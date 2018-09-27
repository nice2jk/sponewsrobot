package com.sponews.batch.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sponews.batch.common.Constants;
import com.sponews.batch.model.ProtoVO;
import com.sponews.batch.utils.HttpUtils;
import com.sponews.batch.utils.ParseUtils;

public class BaseService {

	protected String getContents(String url) {
		HttpUtils httpUtils = HttpUtils.getInstance();
		
		return httpUtils.getHttpHTML(url);
	}
	
	protected List<ProtoVO> getProtoList(String protoContents) {
		List<ProtoVO> protoList = new ArrayList<ProtoVO>();
		
		Document doc = Jsoup.parse(protoContents);
		Elements es = doc.getElementsByAttributeValueContaining("href",Constants.GAME_PROTO_WL);
		
		for(Element el:es) {
			if(el.getElementsByAttributeValue("class", "tit").size() > 0) {
				ProtoVO protoVO = new ProtoVO();
				
				protoVO.setNum(ParseUtils.getNum(el.text()));
				protoVO.setUrl(Constants.HOST_MAIN + el.getElementsByTag("a").attr("href"));
				
				protoList.add(protoVO);
			}
		}
		
		return protoList;
	}
}
