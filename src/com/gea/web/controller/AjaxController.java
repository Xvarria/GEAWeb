package com.gea.web.controller;

import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_GET;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_NOT_FOUND;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gea.web.model.Medidor;
import com.gea.web.model.exception.ControllerException;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.service.MedidorBO;

@Controller
public class AjaxController extends BasicController{
	
	@Autowired
	private MedidorBO medidorBo;
		
	final static Logger log = Logger.getLogger(AjaxController.class);
	
	@RequestMapping("ajax/getMedidorList")
	public @ResponseBody String ajaxMedidorList() throws GeaWebException{
		log.debug("Llamado asincrónico para obtener Medidor");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Collection<Medidor> medidores = this.medidorBo.listMedidor();
			return mapper.writeValueAsString(medidores);
		} catch (GeaWebException e) {
			log.error("Error en el proceso obtener medidores", e);
			throw e;
		} catch (JsonProcessingException e) {
			log.error("Error en llamado asincrónico para obtener medidores", e);
			throw new GeaWebException(e);
		}
	}
	
	@RequestMapping("ajax/getLecturaList")
	public @ResponseBody String ajaxLecturaList(@RequestParam(value = "medidorId", required=true)String medidorIdStr) throws GeaWebException, ControllerException{
		log.debug("Llamado asincrónico para obtener Lecturas");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Medidor medidor = this.getMedidorFormParamater(medidorIdStr);
			return mapper.writeValueAsString(medidor.getLecturas());
		} catch (JsonProcessingException e) {
			log.error("Error en llamado asincrónico para obtener medidores", e);
			throw new GeaWebException(e);
		}
	}
	
	/**
	 * Get Medidor for database and handle it if null
	 * @param medidorIdStr
	 * @return
	 * @throws ControllerException
	 */
	private Medidor getMedidorFormParamater(String medidorIdStr) throws ControllerException  {
		try {
			int id = this.getIdDesdeParametro(medidorIdStr);
			Medidor medidor = this.medidorBo.getMedidorById(id);
			if (medidor == null) {
				throw new ControllerException(MEDIDOR_ERROR_NOT_FOUND);
			}
			return medidor;
		} catch (GeaWebException e) {
			throw new ControllerException(MEDIDOR_ERROR_GET);
		}
	}

}
