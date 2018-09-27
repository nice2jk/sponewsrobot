package com.sponews.batch.dao.sqlservice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sqlmap")
@XmlAccessorType(XmlAccessType.FIELD)
public class SqlList {

	@XmlElement(name="sql")
	private List<SqlData> list = null;

	public void setList(List<SqlData> list) {
		this.list = list;
	}

	public List<SqlData> getList() {
		return list;
	}
}
