package com.sponews.batch.controller;

import com.sponews.batch.service.MatchService;

public class MatchController extends BaseController {

	private MatchService matchService;
	
	public MatchController() {
		matchService = (MatchService) this.context.getBean("matchService");
		System.out.println("match service" + matchService);
	}
	
	public void processWeb() {
		this.matchService.setMatchList();		
	}
}
