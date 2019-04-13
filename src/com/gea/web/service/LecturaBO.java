package com.gea.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.model.response.ProcesoResponse;

/**
 * Interface de servicio para lectura
 * @author mchavarria
 * @since 13/04/2019
 */
public interface LecturaBO {
	
	/**
	 * Procesa la lectura en la base de datos
	 * @param lecturaRequest
	 * @return
	 */
	public ProcesoResponse registraLectura(JsonNode lecturaRequest);

}
