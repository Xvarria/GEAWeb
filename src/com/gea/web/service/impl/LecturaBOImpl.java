/**
 * 
 */
package com.gea.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.dao.LecturaDAO;
import com.gea.web.model.Lectura;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.model.response.ProcesoResponse;
import com.gea.web.service.LecturaBO;

/**
 * Implementación de Servicio a datos para lectura
 * @author mchavarria
 * @since 13/04/2019
 */
@Service
public class LecturaBOImpl implements LecturaBO {

	@Autowired
	private LecturaDAO lecturaDAO;
	
	/* (non-Javadoc)
	 * @see com.gea.web.service.LecturaBO#registraLectura(com.fasterxml.jackson.databind.JsonNode)
	 */
	@Override
	public ProcesoResponse registraLectura(JsonNode lecturaRequest) {
		return this.lecturaDAO.registraLectura(lecturaRequest);
	}

	/**
	 * @see com.gea.web.service.LecturaBO#listLectura()
	 */
	@Override
	public List<Lectura> listLectura() throws GeaWebException {
		// TODO Auto-generated method stub
		return null;
	}
}
