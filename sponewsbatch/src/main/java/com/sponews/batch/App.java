package com.sponews.batch;

import java.util.Map;

import com.sponews.batch.common.LeagueManager;
import com.sponews.batch.controller.MatchController;
import com.sponews.batch.controller.job.MatchThread;
import com.sponews.batch.model.LeagueVO;

public class App {
	public static void main(String[] args) {
		try {
			System.out.println("SpoNews Start!!");
			
			LeagueManager leagueManager = LeagueManager.getInstance();
			Map<String, Object> leagueTable = leagueManager.getLeagueTable();

			for (String leagueKey : leagueTable.keySet()) {
				LeagueVO leagueVO = (LeagueVO) leagueTable.get(leagueKey);

				MatchController matchController = new MatchController(leagueKey, leagueVO.getUrl(), leagueVO.getThreadTime());
			
				MatchThread matchThread = new MatchThread(matchController);
				matchThread.start();
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
