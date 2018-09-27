package com.sponews.batch.dao.sqlservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;

public class SqlService {

private Map<String, String> sqlMap = new HashMap<String, String>();
	
	private SqlList sqlList;
	
	private String fileName = null;
	
	public SqlService() {
		
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
		
		JAXBContext j;
		InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
				
		try {
			j = JAXBContext.newInstance(SqlList.class);
			sqlList = (SqlList)j.createUnmarshaller().unmarshal(in);
			
			for(int i = 0; i < sqlList.getList().size(); i++) {
				sqlMap.put(sqlList.getList().get(i).getKey(), sqlList.getList().get(i).getSql());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSize() {
		return sqlList.getList().size();
	}
	
	public String getSql(String key) {
		String sql = sqlMap.get(key);
		
		if(sql == null) {
			return null;
		} else {
			return sql;
		}
	}
}
