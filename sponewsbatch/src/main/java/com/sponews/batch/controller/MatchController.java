package com.sponews.batch.controller;

import java.util.Map;

import com.sponews.batch.common.LeagueManager;
import com.sponews.batch.service.MatchService;
import com.sponews.batch.service.SwayService;

public class MatchController extends BaseController {

	private MatchService matchService;
	private SwayService swayService;
	
	public MatchController() {
		matchService = (MatchService) this.context.getBean("matchService");
		swayService = (SwayService) this.context.getBean("swayService");
		
		
	}
	
	public void processWeb() {
		LeagueManager leagueManager = LeagueManager.getInstance();
		
		Map<String, String> leagueTable = leagueManager.getLeagueTable();
		
		for(String leagueKey : leagueTable.keySet()) {
			System.out.println(leagueKey + " | " + leagueTable.get(leagueKey));			
		}
		
		/*this.matchService.setMatchList();*/
		this.swayService.setMatchList("EPL", leagueTable.get("EPL"));
		
		
	}
}
