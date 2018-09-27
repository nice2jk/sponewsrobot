package com.sponews.batch.utils;

import com.sponews.batch.common.Constants;

public class ParseUtils {

	public static int getNum(String numString) {
		StringBuffer sb = new StringBuffer();

		int length = numString.length();

		for (int i = 0; i < length; i++) {
			char curChar = numString.charAt(i);
			if (Character.isDigit(curChar))
				sb.append(curChar);
		}

		if (sb.toString().length() == 0) {
			return 0;
		}

		return Integer.valueOf(sb.toString());
	}

	public static String getLeagueName(String origiName) {
		return origiName.replace("[", "").replace("]", "");
	}

	public static String getHomeName(String origiName) {
		if (origiName.contains("(")) {
			return origiName.substring(0, origiName.indexOf("("));
		}

		return origiName;
	}
	
	public static String getMetaData(String homeName) {
		if(homeName.contains("(")) {
			return homeName.substring(homeName.indexOf("("), homeName.indexOf(")") + 1);
		}
		
		return null;
	}

	public static float getRatio(String ratioString) {
		StringBuffer sb = new StringBuffer();

		int length = ratioString.length();

		for (int i = 0; i < length; i++) {
			char curChar = ratioString.charAt(i);
			if (Character.isDigit(curChar) || Character.toString(curChar).equals("."))
				sb.append(curChar);
		}

		if (sb.toString().length() == 0) {
			return 0;
		}

		return Float.valueOf(sb.toString());
	}

	public static int getMatchType(String homeTeam) {
		if (homeTeam.contains("(H")) {
			return Constants.MATCH_TYPE_HANDI;
		} else if (homeTeam.contains("(U")) {
			return Constants.MATCH_TYPE_UNDER;
		}

		return Constants.MATCH_TYPE_NORMAL;
	}
	
	public static String getResult(String origiStr) {
		if(origiStr.contains("무")) {
			return Constants.MATCH_RESULT_DRAW;
		} else if(origiStr.contains("패")) {
			return Constants.MATCH_RESULT_LOSE;
		} else if(origiStr.contains("O")) {
			return Constants.MATCH_RESULT_OVER;
		} else if(origiStr.contains("U")) {
			return Constants.MATCH_RESULT_UNDER;	
		}
		
		return Constants.MATCH_RESULT_WIN;
	}
}
