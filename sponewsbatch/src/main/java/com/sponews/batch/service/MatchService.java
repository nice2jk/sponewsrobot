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
import com.sponews.batch.dao.MatchDAO;
import com.sponews.batch.model.MatchVO;
import com.sponews.batch.model.ProtoVO;
import com.sponews.batch.utils.ParseUtils;


public class MatchService extends BaseService{

	private MatchDAO matchDAO;

	public void setMatchDAO(MatchDAO matchDAO) {
		this.matchDAO = matchDAO;
	}
	
	public void setMatchList() {
		List<ProtoVO> protoList = this.getProtoList(getContents(Constants.HOST_MATCH));
		System.out.println("Proto Size = " + protoList.size());
		
		for(int i = 0; i < protoList.size(); i++) {
			System.out.println("Proto = " + protoList.get(i).getNum() + " | URL = " + protoList.get(i).getUrl());
			
			List<MatchVO> matchList = getMatchList(protoList.get(i));
			
			System.out.println("Proto = " + protoList.get(i).getNum() + " | Match List = " + matchList.size());
			System.out.println("set Match : " + insertMatchList(matchList));
		}
	}
	
	private List<MatchVO> getMatchList(ProtoVO protoVO) {
		List<MatchVO> matchList = new ArrayList<MatchVO>();
		
		Document doc = Jsoup.parse(getContents(protoVO.getUrl()));
		Elements es = doc.getElementsByTag("li");
		
		for(Element el:es) {
			if(el.getElementsByAttributeValue("class", "game").text() == null || el.getElementsByAttributeValue("class", "game").text().equals("")) {
				continue;
			}
			
			MatchVO matchVO = new MatchVO();
			
			/*info*/
			String info = el.getElementsByAttributeValue("class", "info").text();
			
			info = info.replace(" | ", ",");
			String[] infos = info.split(",");
			
			DateFormat sf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			
			try {
				Date d = sf.parse(infos[0]);
				matchVO.setMatchTime(new Timestamp(d.getTime()));
				
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				
				int no = Integer.valueOf(el.getElementsByAttributeValue("class", "num").text());
				matchVO.setMatchId(c.get(Calendar.YEAR) + "" + protoVO.getNum() + "" + String.format("%03d", no));
			} catch (Exception e) {
				System.out.println(infos[0]);
			}
			
			/*sports*/
			String sports = el.getElementsByTag("img").first().attr("src");
			matchVO.setSports(sports.substring(sports.indexOf("_") + 1, sports.indexOf(".")));
			
			/*league*/
			matchVO.setLeague(ParseUtils.getLeagueName(el.getElementsByTag("strong").text()));
			
			/*game*/
			String game = el.getElementsByAttributeValue("class", "game").text();
			game = game.replace(el.getElementsByTag("strong").text(), "");
			
			String[] split = game.split("VS");
			
			if(split[0].contains("미정") || split[1].contains("미정")) {
				System.out.println(protoVO.getNum() + " | " + matchVO.getMatchId() + "no name");				
				continue;
			}
			
			matchVO.setHomeTeam(ParseUtils.getHomeName(split[0]).trim());
			matchVO.setAwayTeam(split[1].trim());
			
			/*ratio*/
			matchVO.setHomeRatio(ParseUtils.getRatio(infos[1]));
			matchVO.setDrawRatio(ParseUtils.getRatio(infos[2]));
			matchVO.setAwawyRatio(ParseUtils.getRatio(infos[3]));
			
			/*type*/
			matchVO.setMatchType(ParseUtils.getMatchType(split[0]));
			
			/*Handi&UnOver*/
			matchVO.setDescription(ParseUtils.getMetaData(split[0]));
		
			/*set match info*/
			matchList.add(matchVO);
			
			/*if(matchVO.getType().equals(Constants.MATCH_TYPE_NORMAL)) {
				matchList.add(matchVO);
			}*/
		}
		
		return matchList;		
	}
	
	@Transactional
	private int insertMatchList(List<MatchVO> matchList) {
		int result = 0;
		
		for(int i = 0; i < matchList.size(); i++) {
			try {
				if(Constants.USE_DB_YN) {
					int count = matchDAO.getMatch(matchList.get(i).getMatchId());
										
					if(count > 0) {
						result += matchDAO.updateMatch(matchList.get(i).getMatchId(), matchList.get(i).getHomeRatio(), matchList.get(i).getDrawRatio(), matchList.get(i).getAwawyRatio(), matchList.get(i).getDescription(), matchList.get(i).getMatchTime());
					} else {
						result += matchDAO.insertMatch(matchList.get(i));
					}	
				} else {
					System.out.println(matchList.get(i));
				}
			} catch (Exception e) {
				System.out.println("match insert exception : " + matchList.get(i).getMatchId() + " | " + e.getMessage());
			}
		}
		
		return result;
	}
	
	public void getTestMatchCount() {
		System.out.println(matchDAO.getMatch("201863000"));
		System.out.println(matchDAO.getMatch("2018630"));
	}
	
}
