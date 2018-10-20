package com.sponews.batch.model;

public class SwayMatchVO {

	private String matchId;
	private String league;
	private String description;
	private int matchMonth;
	private String matchTime;
	private String homeTeam;
	private String awayTeam;
	private float homeRatio;
	private float drawRatio;
	private float awawyRatio;
	private String score;
	private int result;
	private int status;

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMatchMonth() {
		return matchMonth;
	}

	public void setMatchMonth(int matchMonth) {
		this.matchMonth = matchMonth;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public float getHomeRatio() {
		return homeRatio;
	}

	public void setHomeRatio(float homeRatio) {
		this.homeRatio = homeRatio;
	}

	public float getDrawRatio() {
		return drawRatio;
	}

	public void setDrawRatio(float drawRatio) {
		this.drawRatio = drawRatio;
	}

	public float getAwawyRatio() {
		return awawyRatio;
	}

	public void setAwawyRatio(float awawyRatio) {
		this.awawyRatio = awawyRatio;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SwayMatchVO [matchId=" + matchId + ", league=" + league + ", description=" + description
				+ ", matchMonth=" + matchMonth + ", matchTime=" + matchTime + ", homeTeam=" + homeTeam + ", awayTeam="
				+ awayTeam + ", homeRatio=" + homeRatio + ", drawRatio=" + drawRatio + ", awawyRatio=" + awawyRatio
				+ ", score=" + score + ", result=" + result + ", status=" + status + "]";
	}

}
