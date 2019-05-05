package com.gea.web.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gea.web.model.Medidor;
import com.gea.web.service.MedidorBO;

@Controller
public class AjaxController {
	
	@Autowired
	private MedidorBO medidorBo;
	
	final static Logger log = Logger.getLogger(AjaxController.class);
	
	@RequestMapping("/ajaxGetCagetorias")
	public @ResponseBody String ajaxGetCagetorias() throws Exception{
		log.info("Llamado asincrónico para obtener Categorias");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Medidor> medidores = this.medidorBo.listMedidor();
			return mapper.writeValueAsString(medidores);
		} catch (JsonProcessingException e) {
			log.info("Error en llamado asincrónico para obtener medidores", e);
			throw e;
		}
	}

}
