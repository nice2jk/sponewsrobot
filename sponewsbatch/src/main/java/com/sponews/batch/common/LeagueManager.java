package com.sponews.batch.common;

import java.util.HashMap;
import java.util.Map;

import com.sponews.batch.model.LeagueVO;

public class LeagueManager {
	
	public static LeagueManager instance;
	
	private Map<String, Object> leagueTable;

	public static LeagueManager getInstance() {
		if(instance == null) {
			instance = new LeagueManager();
		}
		
		return instance;
	}
	
	public LeagueManager() {
		leagueTable = new HashMap<String, Object>();
		
		leagueTable.put("EPL", new LeagueVO("EPL", "https://kr.soccerway.com/national/england/premier-league/20182019/regular-season/r48730/matches/", 10800000));
		leagueTable.put("GPL", new LeagueVO("GPL", "https://kr.soccerway.com/national/germany/bundesliga/20182019/regular-season/r47657/matches/", 10800000));
		leagueTable.put("SPL", new LeagueVO("SPL", "https://kr.soccerway.com/national/spain/primera-division/20182019/regular-season/r47983/matches/", 10800000));
		leagueTable.put("IPL", new LeagueVO("IPL", "https://kr.soccerway.com/national/italy/serie-a/20182019/regular-season/r48235/matches/", 10800000));
		leagueTable.put("FPL", new LeagueVO("FPL", "https://kr.soccerway.com/national/france/ligue-1/20182019/regular-season/r48044/matches/", 10800000));
		leagueTable.put("UCL", new LeagueVO("UCL", "https://kr.soccerway.com/international/europe/uefa-champions-league/20182019/group-stage/r48410/matches/", 21600000));
													
	}
	
	public Map<String, Object> getLeagueTable() {
		return leagueTable;
	}
}
