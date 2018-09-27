package com.sponews.batch.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;

import com.sponews.batch.common.Constants;
import com.sponews.batch.dao.ResultDAO;
import com.sponews.batch.model.MatchVO;
import com.sponews.batch.model.ProtoVO;
import com.sponews.batch.utils.ParseUtils;

public class ResultService extends BaseService {

	private ResultDAO resultDAO;

	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}

	public void setResultList() {
		/*getContents("http://m.betman.co.kr/winningResultProto.so?method=detail&gameId=G101&gameRound=180067&page=1");*/
		getContents("https://kr.soccerway.com/national/england/premier-league/20182019/regular-season/r48730/");
		/*System.out.println(getContents("http://m.betman.co.kr/winningResultProto.so?method=detail&gameId=G101&gameRound=180066&page=1"));*/
		
		/*List<ProtoVO> protoList = this.getProtoList(getContents(Constants.HOST_RESULT));
		System.out.println("Result Proto Size = " + protoList.size());
		
		for(int i = 0; i < protoList.size(); i++) {
			System.out.println("Result Proto = " + protoList.get(i).getNum() + " | URL = " + protoList.get(i).getUrl());
			
			List<MatchVO> resultList = getResultList(protoList.get(i));
			
			System.out.println("Proto = " + protoList.get(i).getNum() + " | Match List = " + resultList.size());
			System.out.println("set Match : " + insertResultList(resultList));
		}*/
	}
	
	private List<MatchVO> getResultList(ProtoVO protoVO) {
		List<MatchVO> matchList = new ArrayList<MatchVO>();
		
		System.out.println(getContents(protoVO.getUrl()));
		Document doc = Jsoup.parse(getContents(protoVO.getUrl()));
		Elements es = doc.getElementsByAttributeValue("class", "simple");
		
		for(Element el:es) {
			if(el.getElementsByAttributeValue("class", "game").text() == null || el.getElementsByAttributeValue("class", "game").text().equals("")) {
				continue;
			}
			
			if(el.getElementsByTag("a").attr("onclick") == null || el.getElementsByTag("a").attr("onclick").equals("")) {				
				continue;
			}
			
			/*match*/
			String game = el.getElementsByAttributeValue("class", "game").text();
			
			String[] split = game.split("VS");
			
			if(split[0].contains("미정") || split[1].contains("미정")) {
				continue;
			}
			
			MatchVO matchVO = new MatchVO();
			
			/*result*/
			String result = el.getElementsByTag("a").attr("onclick");
			
			String[] results = result.split("\'");
			
			DateFormat sf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			
			try {
				Date d = sf.parse(results[3]);
				matchVO.setMatchTime(new Timestamp(d.getTime()));
				
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				
				int no = Integer.valueOf(el.getElementsByAttributeValue("class", "num").text());
				matchVO.setMatchId(c.get(Calendar.YEAR) + "" + protoVO.getNum() + "" + String.format("%03d", no));				
			} catch (Exception e) {
				System.out.println(results[3]);
			}
			
			matchVO.setHomeRatio(ParseUtils.getRatio(results[7]));
			matchVO.setDrawRatio(ParseUtils.getRatio(results[9]));
			matchVO.setAwawyRatio(ParseUtils.getRatio(results[11]));
			
			matchVO.setScore(results[13]);
			
			if(matchVO.getScore().equals("취소")) {
				matchVO.setResult(Constants.MATCH_RESULT_CANCEL);
			} else {
				matchVO.setResult(ParseUtils.getResult(el.getElementsByAttributeValue("class", "result").select("[alt]").attr("alt")));	
			}
		}
		
		return matchList;
	}
	
	@Transactional
	private int insertResultList(List<MatchVO> resultList) {
		int result = 0;
		
		for(int i = 0; i < resultList.size(); i++) {
			try {
				if(Constants.USE_DB_YN) {
					
				} else {
					System.out.println(resultList.get(i));
				}
			} catch (Exception e) {
				System.out.println("result insert exception : " + resultList.get(i).getMatchId() + " | " + e.getMessage());
			}
		}
		
		return result;
	}
}
