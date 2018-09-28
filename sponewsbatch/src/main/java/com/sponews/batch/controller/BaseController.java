package com.sponews.batch.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BaseController {

	protected ApplicationContext context;
	
	public BaseController() {
		context = new GenericXmlApplicationContext("applicationContext.xml"); 
		System.out.println("on load");
	}
}
