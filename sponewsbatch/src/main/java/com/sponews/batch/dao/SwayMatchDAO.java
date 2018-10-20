package com.sponews.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sponews.batch.dao.sqlservice.SqlService;
import com.sponews.batch.model.SwayMatchVO;


public class SwayMatchDAO {

	private SqlService sqlService;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int inserSwaytMatch(final SwayMatchVO smvo) {
		return this.jdbcTemplate.update(this.sqlService.getSql("insertSwayMatch"), new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, smvo.getMatchId());
				ps.setString(2, smvo.getLeague());
				ps.setString(3, smvo.getDescription());
				ps.setInt(4, smvo.getMatchMonth());
				ps.setString(5, smvo.getMatchTime());
				ps.setString(6, smvo.getHomeTeam());
				ps.setString(7, smvo.getAwayTeam());
				ps.setInt(8, smvo.getStatus());
				ps.setString(9, smvo.getScore());
			}
		});
	}
	
	public int updateSwayMatch(String matchId, int status, String score, String matchTime, int result) {
		return this.jdbcTemplate.update(this.sqlService.getSql("updateSwayMatch"), status, score, matchTime, result, matchId);
	}
	
	public int getSwayMatch(String matchId) {
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("getSwayMatch"), new Object[] {matchId}, Integer.class);
	}
	
	public List<SwayMatchVO> getAllMatch() {
		List<Map<String, Object>> list =  this.jdbcTemplate.queryForList(this.sqlService.getSql("getAllMatch"));
		
		System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			String score = (String) map.get("score");
			
			if(score.contains("-")) {
				String[] split = score.split("-");
				
				int home = Integer.valueOf(split[0].trim());
				int away = Integer.valueOf(split[1].trim());
				int matchId = (Integer)map.get("match_id");
				
				if(home > away) {
					System.out.println(i + " " + matchId + " " + score + " 1");
					updateSwayMatch(matchId, 1);
				} else if (home < away) {
					System.out.println(i + " " + matchId + " " + score + " 2");
					updateSwayMatch(matchId, 2);
				} else {
					System.out.println(i + " " + matchId + " " + score + " 0");
					updateSwayMatch(matchId, 0);
				}
			}
		}
		
		return null;
	}
	
	public int updateSwayMatch(int matchId, int result) {
		return this.jdbcTemplate.update(this.sqlService.getSql("updateSwayMatchResult"), result, matchId);
	}
}
