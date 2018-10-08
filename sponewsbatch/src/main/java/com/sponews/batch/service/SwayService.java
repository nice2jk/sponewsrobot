package com.sponews.batch.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sponews.batch.common.Constants;
import com.sponews.batch.dao.SwayMatchDAO;
import com.sponews.batch.model.SwayMatchVO;

public class SwayService extends BaseService {

	private SwayMatchDAO swayMatchDAO;

	public void setSwayMatchDAO(SwayMatchDAO swayMatchDAO) {
		this.swayMatchDAO = swayMatchDAO;
	}
	
	public void setMatchList(String league, String url) {
		List<SwayMatchVO> matchList = getSwayMatchList(league, url);
		System.out.println(league + " match size : " + matchList.size());
		
		for(int i = 0; i < matchList.size(); i++) {
			if(swayMatchDAO.getSwayMatch(matchList.get(i).getMatchId()) > 0) {
				if(matchList.get(i).getStatus() == Constants.MATCH_STATUS_AFTER) {
					swayMatchDAO.updateSwayMatch(matchList.get(i).getMatchId(), Constants.MATCH_STATUS_AFTER, matchList.get(i).getScore(), matchList.get(i).getMatchTime());	
				}
			} else {
				swayMatchDAO.inserSwaytMatch(matchList.get(i));
			}
		}
	}
	
	private List<SwayMatchVO> getSwayMatchList(String league, String url) {
		List<SwayMatchVO> matchList = new ArrayList<SwayMatchVO>();
		
		Document doc = Jsoup.parse(getContents(url));
		Elements es = doc.getElementsByAttributeValueContaining("class", "no-date-repetition");
		
		for(Element el:es) {
			SwayMatchVO swayMatchVO = new SwayMatchVO();
			
			String id = el.attr("id");
			
			swayMatchVO.setMatchId(id.substring(id.lastIndexOf("-") + 1));
			swayMatchVO.setLeague(league);
			swayMatchVO.setHomeTeam(el.getElementsByAttributeValueContaining("class", "team-a").text());
			swayMatchVO.setAwayTeam(el.getElementsByAttributeValueContaining("class", "team-b").text());
			swayMatchVO.setMatchTime(el.attr("data-timestamp"));
			
			String score = el.getElementsByAttributeValueContaining("class", "score").text();
			
			if(score.contains("-")) {
				swayMatchVO.setScore(score);
				swayMatchVO.setStatus(Constants.MATCH_STATUS_AFTER);
			} else {
	    		SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
	    		swayMatchVO.setScore(dt.format(new Date((Long.valueOf(swayMatchVO.getMatchTime()) * 1000))));
	    		/*System.out.println(dt.format(new Date((Long.valueOf(swayMatchVO.getMatchTime()) * 1000))));*/
	    		swayMatchVO.setStatus(Constants.MATCH_STATUS_BEFORE);
			}
			
			/*System.out.println(swayMatchVO);*/
			
			matchList.add(swayMatchVO);
		}
		
		return matchList;
	}
}
