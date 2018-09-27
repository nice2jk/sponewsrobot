package com.sponews.batch.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sponews.batch.dao.sqlservice.SqlService;
import com.sponews.batch.model.MatchVO;


public class MatchDAO {

	private SqlService sqlService;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insertMatch(final MatchVO mvo) {
		return this.jdbcTemplate.update(this.sqlService.getSql("insertMatch"), new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvo.getMatchId());
				ps.setString(2, mvo.getSports());
				ps.setString(3, mvo.getLeague());
				ps.setString(4, mvo.getDescription());
				ps.setTimestamp(5, mvo.getMatchTime());
				ps.setString(6, mvo.getHomeTeam());
				ps.setString(7, mvo.getAwayTeam());
				ps.setInt(8, mvo.getMatchType());
				ps.setFloat(9, mvo.getHomeRatio());
				ps.setFloat(10, mvo.getDrawRatio());
				ps.setFloat(11, mvo.getAwawyRatio());
			}
		});
	}
	
	public int updateMatch(String matchId, float home_ratio, float draw_ratio, float away_ratio, String description, Timestamp time) {
		return this.jdbcTemplate.update(this.sqlService.getSql("updateMatch"), home_ratio, draw_ratio, away_ratio, description, time, matchId);
	}
	
	public int getMatch(String matchId) {
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("getMatch"), new Object[] {matchId}, Integer.class);
	}
}
