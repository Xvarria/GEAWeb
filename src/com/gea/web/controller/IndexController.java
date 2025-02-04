package com.gea.web.controller;

import static com.gea.web.model.constant.GeaWebConstants.HEARTBEAT_DEFAULT_MESSAGE;
import static com.gea.web.model.constant.GeaWebConstants.HEARTBEAT_MESSAGE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController extends BasicController{
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/hartbeat", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String hartbeat() {
		String mensaje = this.propertyMessageBO.getMessageFromProperties(HEARTBEAT_MESSAGE, HEARTBEAT_DEFAULT_MESSAGE);
		return mensaje;
	}
}
