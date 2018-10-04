package com.sponews.batch.common;

import java.util.HashMap;
import java.util.Map;

public class LeagueManager {
	
	public static LeagueManager instance;
	
	private Map<String, String> leagueTable;

	public static LeagueManager getInstance() {
		if(instance == null) {
			instance = new LeagueManager();
		}
		
		return instance;
	}
	
	public LeagueManager() {
		leagueTable = new HashMap<String, String>();
		
		leagueTable.put("EPL", "https://kr.soccerway.com/national/england/premier-league/20182019/regular-season/r48730/matches/");
		leagueTable.put("GPL", "https://kr.soccerway.com/national/germany/bundesliga/20182019/regular-season/r47657/matches/");
		leagueTable.put("SPL", "https://kr.soccerway.com/national/spain/primera-division/20182019/regular-season/r47983/matches/");
		leagueTable.put("IPL", "https://kr.soccerway.com/national/italy/serie-a/20182019/regular-season/r48235/matches/matches/");
		leagueTable.put("FPL", "https://kr.soccerway.com/national/france/ligue-1/20182019/regular-season/r48044/matches/");
		leagueTable.put("UCL", "https://kr.soccerway.com/international/europe/uefa-champions-league/20182019/play-offs/r48411/matches/");		
	}
	
	public Map<String, String> getLeagueTable() {
		return leagueTable;
	}
}
