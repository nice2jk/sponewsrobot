package com.sponews.batch.common;

public class Constants {

	public static boolean USE_DB_YN = false;
	
	public static int MATCH_THREAD_TIME = 10800000;
	public static int RESULT_THREAD_TIME = 3600000;
	public static int USER_THREAD_TIME = 5400000;
	
	public static String HOST_MAIN = "http://m.betman.co.kr/";
	public static String HOST_MATCH = "http://m.betman.co.kr/buyableGame.so?method=list";
	public static String HOST_RESULT = "http://m.betman.co.kr/winningResultList.so";
	
	public static String GAME_PROTO_WL = "G101";
	
	public static int MATCH_TYPE_NORMAL = 0;
	public static int MATCH_TYPE_HANDI = 1;
	public static int MATCH_TYPE_UNDER = 2;
	
	public static String SPORTS_SOCCER = "soccer";
	public static String SPORTS_BASEBALL = "baseball";
	public static String SPORTS_BASKETBALL = "basketball";
	public static String SPORTS_VOLLEYBALL = "volleyball";
	
	public static String MATCH_RESULT_WIN = "WIN";
	public static String MATCH_RESULT_DRAW = "DRAW";
	public static String MATCH_RESULT_LOSE = "LOSE";
	public static String MATCH_RESULT_OVER = "OVER";
	public static String MATCH_RESULT_UNDER = "UNDER";
	public static String MATCH_RESULT_CANCEL = "CANCEL";
	
	public static int RESULT_WIN = 1;
	public static int RESULT_DRAW = 0;
	public static int RESULT_LOSE = 2;
	
	public static int MATCH_STATUS_BEFORE = 0;
	public static int MATCH_STATUS_AFTER = 1;	
	
	public static String SWAY_EPL_URL = "https://kr.soccerway.com/international/europe/uefa-champions-league/20182019/play-offs/r48411/matches/";
	
	// 30min, 1h, 2h, 3h, 4h, 5h, 6h, 10h
	public static final int[] FETCH_INTERVAL = {
		1800000, 3600000, 7200000, 10800000, 14400000, 18000000, 21600000, -1, -1, -1, 36000000
	};
}
