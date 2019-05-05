package com.gea.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gea.web.model.exception.GeaWebException;
 
 /**
 * @author mchavarria
 *
 */
@Repository
public abstract class AbstractDAO {
 
	@PersistenceContext(name="persistenceUnit")
	protected EntityManager entityManager;

 
    protected void save(Object entity) throws GeaWebException {
    	try {
			this.entityManager.persist(entity);
		} catch (Exception e) {
			new GeaWebException("Error al ejecutar el mÃ©todo save del DAO", e);
		}
    }
 
    protected void delete(Object entity) throws GeaWebException {
    	try {
        	this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
		} catch (Exception e) {
			new GeaWebException("Error al ejecutar el mÃ©todo delete del DAO", e);
		}
    }
    
    protected void update(Object entity) throws GeaWebException {
    	try {
    		this.entityManager.merge(entity);
		} catch (Exception e) {
			new GeaWebException("Error al ejecutar el mÃ©todo update del DAO", e);
		}    	
    }
}
