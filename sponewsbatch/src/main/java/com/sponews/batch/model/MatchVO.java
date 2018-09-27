package com.sponews.batch.model;

import java.sql.Timestamp;

public class MatchVO {

	private String matchId;
	private String sports;
	private String league;
	private String description;
	private Timestamp matchTime;
	private String homeTeam;
	private String awayTeam;
	private int matchType;
	private float homeRatio;
	private float drawRatio;
	private float awawyRatio;
	private String score;
	private String result;

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
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

	public Timestamp getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Timestamp matchTime) {
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

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "MatchVO [matchId=" + matchId + ", sports=" + sports + ", league=" + league + ", description="
				+ description + ", matchTime=" + matchTime + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", matchType=" + matchType + ", homeRatio=" + homeRatio + ", drawRatio=" + drawRatio + ", awawyRatio="
				+ awawyRatio + ", score=" + score + ", result=" + result + "]";
	}

}
