package com.sponews.batch.common;

public class Constants {

	public static boolean USE_DB_YN = false;
	
	public static int MATCH_THREAD_TIME = 1800000;
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
}
