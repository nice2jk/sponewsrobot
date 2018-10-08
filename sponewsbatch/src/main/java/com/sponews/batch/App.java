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
			
			/*
			 * System.out.println(new Date(1538220600L * 1000)); SimpleDateFormat dt = new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm"); System.out.println(dt.format(new
			 * Date(1538420400L * 1000)));
			 */
			/*
			 * ResultController resultController = new ResultController();
			 * resultController.processWeb();
			 */

			/*
			 * String crawlStorageFolder = "/data/crawl/root"; int numberOfCrawlers = 7;
			 * 
			 * CrawlConfig config = new CrawlConfig();
			 * config.setCrawlStorageFolder(crawlStorageFolder);
			 * 
			 * 
			 * Instantiate the controller for this crawl.
			 * 
			 * PageFetcher pageFetcher = new PageFetcher(config); RobotstxtConfig
			 * robotstxtConfig = new RobotstxtConfig(); RobotstxtServer robotstxtServer =
			 * new RobotstxtServer(robotstxtConfig, pageFetcher); CrawlController controller
			 * = new CrawlController(config, pageFetcher, robotstxtServer);
			 * 
			 * 
			 * For each crawl, you need to add some seed urls. These are the first URLs that
			 * are fetched and then the crawler starts following links which are found in
			 * these pages
			 * 
			 * controller.addSeed("http://www.daum.net");
			 * controller.addSeed("http://www.google.co.kr"); controller.addSeed(
			 * "http://m.betman.co.kr/winningResultProto.so?method=detail&gameId=G101&gameRound=180067"
			 * );
			 * 
			 * 
			 * Start the crawl. This is a blocking operation, meaning that your code will
			 * reach the line after this only when crawling is finished.
			 * 
			 * controller.start(CralwerService.class, numberOfCrawlers);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
