package com.sponews.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
				ps.setString(4, smvo.getMatchTime());
				ps.setString(5, smvo.getHomeTeam());
				ps.setString(6, smvo.getAwayTeam());
				ps.setInt(7, smvo.getStatus());
				ps.setString(8, smvo.getScore());
			}
		});
	}
	
	public int updateSwayMatch(String matchId, String score, String matchTime) {
		return this.jdbcTemplate.update(this.sqlService.getSql("updateSwayMatch"), score, matchTime, matchId);
	}
	
	public int getSwayMatch(String matchId) {
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("getSwayMatch"), new Object[] {matchId}, Integer.class);
	}
}
