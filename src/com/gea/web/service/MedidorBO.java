package com.gea.web.service;

import java.util.List;

import com.gea.web.model.Medidor;
import com.gea.web.model.exception.GeaWebException;

/**
 * @author mchavarria
 *
 */
public interface MedidorBO {
	
	/**
	 * Crea un nuevo registro de medidor en la base de datos
	 * @param medidor
	 * @throws GeaWebException
	 */
	public void save(Medidor medidor) throws GeaWebException;
	
	/**
	 * Actualiza el objeto medidor en la base de datos
	 * @param medidor
	 * @throws GeaWebException
	 */
	public void update(Medidor medidor) throws GeaWebException;
	
	/**
	 * Elimina el objeto medidor de la base de datos
	 * @param medidor
	 * @throws GeaWebException
	 */
	public void delete(Medidor medidor) throws GeaWebException;
	
	/**
	 * Obtiene medidor por Id
	 *  	 
	 * @param id
	 * @return
	 * @throws GeaWebException
	 */
	public Medidor getMedidorById(int medidorId) throws GeaWebException;
	
	/**
	 * Obtiene la lista de medidor de la base de datos
	 * @return
	 * @throws GeaWebException
	 */
	public List<Medidor> listMedidor() throws GeaWebException;
}
