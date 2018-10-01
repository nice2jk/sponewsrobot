package com.sponews.batch.controller;

import com.sponews.batch.service.MatchService;
import com.sponews.batch.service.SwayService;

public class MatchController extends BaseController {

	private MatchService matchService;
	private SwayService swayService;
	
	public MatchController() {
		matchService = (MatchService) this.context.getBean("matchService");
		swayService = (SwayService) this.context.getBean("swayService");
	}
	
	public void processWeb(String league) {
		/*this.matchService.setMatchList();*/
		this.swayService.setMatchList(league);
	}
}
