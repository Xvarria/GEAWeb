package com.gea.web.dao;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.model.Lectura;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.model.response.ProcesoResponse;

/**
 * Interface de Acceso a datos para lectura
 * @author mchavarria
 * @since 13/04/2019
 */
public interface LecturaDAO {
	
	/**
	 * Procesa la lectura en la base de datos
	 * @param lecturaRequest
	 * @return
	 */
	public ProcesoResponse registraLectura(JsonNode lecturaRequest);
	
	/**
	 * Obtiene la lista de lecturas de la base de datos por medidorId
	 * @return
	 * @throws GeaWebException
	 */
	public List<Lectura> listLectura() throws GeaWebException;	
	
}
