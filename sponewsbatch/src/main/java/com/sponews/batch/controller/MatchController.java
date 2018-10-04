package com.sponews.batch.controller;

import com.sponews.batch.service.MatchService;
import com.sponews.batch.service.SwayService;

public class MatchController extends BaseController {

	private MatchService matchService;
	private SwayService swayService;

	private String leagueName;
	private String leagueUrl;
	private int interval;

	public MatchController(String leagueName, String leagueUrl, int interval) {
		matchService = (MatchService) this.context.getBean("matchService");
		swayService = (SwayService) this.context.getBean("swayService");

		this.leagueName = leagueName;
		this.leagueUrl = leagueUrl;
		this.interval = interval;
	}

	public void processWeb() {
		this.swayService.setMatchList(this.leagueName, this.leagueUrl);
	}

	public int getInterval() {
		return this.interval;
	}

	public String getLeagueName() {
		return leagueName;
	}

}
