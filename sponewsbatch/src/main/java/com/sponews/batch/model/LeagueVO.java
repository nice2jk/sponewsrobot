package com.sponews.batch.model;

public class LeagueVO {

	public String league;
	public String url;
	public int threadTime;

	public LeagueVO(String league, String url, int threadTime) {
		super();
		this.league = league;
		this.url = url;
		this.threadTime = threadTime;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getThreadTime() {
		return threadTime;
	}

	public void setThreadTime(int threadTime) {
		this.threadTime = threadTime;
	}

	@Override
	public String toString() {
		return "LeagueVO [league=" + league + ", url=" + url + ", threadTime=" + threadTime + "]";
	}

}
