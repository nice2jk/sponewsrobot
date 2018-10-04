package com.sponews.batch.controller.job;

import com.sponews.batch.common.Constants;
import com.sponews.batch.controller.MatchController;

public class MatchThread extends Thread {
	
	MatchController matchController;
	
	public MatchThread(MatchController matchController) {
		this.matchController = matchController;
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(matchController.getLeagueName() + " Thread Start!!!");
				
				matchController.processWeb();
				
				Thread.sleep(matchController.getInterval());
			} catch (Exception e) {
				System.out.println("Match Thread exception " + e.getMessage());
				break;
			}
		}
	}
	
	

}
