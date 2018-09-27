package com.sponews.batch.model;

public class ProtoVO {

	private int num;
	private String url;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ProtoVO [num=" + num + ", url=" + url + "]";
	}
}
