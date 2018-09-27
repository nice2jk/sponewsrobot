package com.sponews.batch.controller;

import com.sponews.batch.service.MatchService;

public class MatchController extends BaseController {

	private MatchService matchService;
	
	public MatchController() {
		matchService = (MatchService) this.context.getBean("matchService");
	}
	
	public void processWeb() {
		this.matchService.setMatchList();		
	}
}
