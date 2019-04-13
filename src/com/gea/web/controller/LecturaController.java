package com.gea.web.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.model.request.LecturaRequest;
import com.gea.web.model.response.CompletationCode;
import com.gea.web.model.response.LecturaResponse;
import com.gea.web.model.response.ProcesoResponse;
import com.gea.web.model.response.ReasonCode;
import com.gea.web.service.LecturaBO;

/**
 * Controlador para lecturas
 * 
 * @author mchavarria
 * @since 13/04/2019
 *
 */
@RestController
@EnableWebMvc
public class LecturaController {
	
	final static Logger log = Logger.getLogger(LecturaController.class);
	
	@Autowired
	private LecturaBO lecturaBO;
	
	@RequestMapping(value = "/service/registrarLectura", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LecturaResponse registrarLectura(@RequestBody LecturaRequest lecturaRequest) {
		long initTime = Calendar.getInstance().getTimeInMillis();
		log.debug("ProcessRequest: " + lecturaRequest.toString());
		long finishTime = Calendar.getInstance().getTimeInMillis();
		return new LecturaResponse(ReasonCode.SUCCESS, CompletationCode.SUCCESS, initTime, finishTime);
	}
	
	@RequestMapping(value = "/service/registrarLecturaRaw", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody LecturaResponse registrarLecturaRaw(@RequestBody JsonNode lecturaRequest) {
		long initTime = Calendar.getInstance().getTimeInMillis();
		log.debug("ProcessRequest: " + lecturaRequest.toString());
		ProcesoResponse procesoReponse = this.lecturaBO.registraLectura(lecturaRequest);
		long finishTime = Calendar.getInstance().getTimeInMillis();
		LecturaResponse response = new LecturaResponse(procesoReponse, initTime, finishTime);
		return response;
	}
	
	@RequestMapping(value = "/service/testLectura", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LecturaResponse testLectura() {
		long initTime = Calendar.getInstance().getTimeInMillis();
		log.debug("Llega acá");
		long finishTime = Calendar.getInstance().getTimeInMillis();
		return new LecturaResponse(ReasonCode.SUCCESS, CompletationCode.SUCCESS, initTime, finishTime);
	}

}
