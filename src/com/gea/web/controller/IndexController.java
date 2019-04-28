package com.gea.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/hartbeat", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String hartbeat() {
		return "Aplicación GEA Web Funcionando";
	}
}
