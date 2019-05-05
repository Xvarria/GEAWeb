package com.gea.web.dao;

import java.util.List;

import com.gea.web.model.Medidor;
import com.gea.web.model.exception.GeaWebException;

/**
 * @author mchavarria
 *
 */
public interface MedidorDAO {
	
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
	 * *Importante* en Hibernate 5 se requiere la dependencia para 
	 * hibernate-jpamodelgen y su respectivo plugin en el POM
	 * Para Eliminar errores de IDE se requiere en las opciones de "Compiler" permitir 
	 * procesamiento de annotaciones y agregar la opcion target/metamodel
	 * https://docs.jboss.org/hibernate/orm/5.0/topical/html/metamodelgen/MetamodelGenerator.html 
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
