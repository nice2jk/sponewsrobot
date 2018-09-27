package com.sponews.batch.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sponews.batch.dao.sqlservice.SqlService;

public class ResultDAO {

	private SqlService sqlService;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
