package com.sponews.batch.controller;

import com.sponews.batch.service.ResultService;

public class ResultController extends BaseController {

	private ResultService resultService;

	public ResultController() {
		this.resultService = (ResultService) this.context.getBean("resultService");
	}
	
	public void processWeb() {
		resultService.setResultList();
	}
	
}
