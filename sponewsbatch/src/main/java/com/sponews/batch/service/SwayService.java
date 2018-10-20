package com.sponews.batch.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
					swayMatchDAO.updateSwayMatch(matchList.get(i).getMatchId(), Constants.MATCH_STATUS_AFTER, matchList.get(i).getScore(), matchList.get(i).getMatchTime(), matchList.get(i).getResult());	
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
			
			Date date = new Date((Long.valueOf(swayMatchVO.getMatchTime()) * 1000));
			Calendar c = Calendar.getInstance();
    		c.setTime(date);
    		swayMatchVO.setMatchMonth(c.get(Calendar.YEAR) * 100 + (c.get(Calendar.MONTH) + 1));
    		
			String score = el.getElementsByAttributeValueContaining("class", "score").text();
			
			if(score.contains("-")) {
				swayMatchVO.setScore(score);
				swayMatchVO.setStatus(Constants.MATCH_STATUS_AFTER);
				
				String[] split = score.split("-");
				
				int home = Integer.valueOf(split[0].trim());
				int away = Integer.valueOf(split[1].trim());
				
				if(home > away) {
					swayMatchVO.setResult(Constants.RESULT_WIN);
				} else if (home < away) {
					swayMatchVO.setResult(Constants.RESULT_LOSE);
				} else {
					swayMatchVO.setResult(Constants.RESULT_DRAW);
				}
				
			} else {
	    		SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
	    		swayMatchVO.setScore(dt.format(date));
	    	
	    		swayMatchVO.setStatus(Constants.MATCH_STATUS_BEFORE);
			}
			
			/*System.out.println(swayMatchVO);*/
			
			matchList.add(swayMatchVO);
		}
		
		return matchList;
	}
	
	public void getAllMatch() {
		swayMatchDAO.getAllMatch();
	}
}
