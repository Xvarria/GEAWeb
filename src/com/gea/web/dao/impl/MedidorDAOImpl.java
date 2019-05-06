/**
 * 
 */
package com.gea.web.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.gea.web.dao.AbstractDAO;
import com.gea.web.dao.MedidorDAO;
import com.gea.web.model.Medidor;
import com.gea.web.model.Medidor_;
import com.gea.web.model.exception.GeaWebException;

/**
 * @author mchavarria
 *
 */
@Repository
public class MedidorDAOImpl extends AbstractDAO implements MedidorDAO {
	
	/**
	 * 
	 * @see com.gea.web.dao.MedidorDAO{s}save(com.gea.web.model.Medidor)
	 */
	@Override
	public void save(Medidor medidor) throws GeaWebException {
		super.save(medidor);
	}

	/**
	 * 
	 * @see com.gea.web.dao.MedidorDAO{s}update(com.gea.web.model.Medidor)
	 */
	@Override
	public void update(Medidor medidor) throws GeaWebException {
		super.update(medidor);
	}

	/**
	 * 
	 * @see com.gea.web.dao.MedidorDAO{s}delete(com.gea.web.model.Medidor)
	 */
	@Override
	public void delete(Medidor medidor) throws GeaWebException {
		super.delete(medidor);
	}

	/**
	 * 
	 * @see com.gea.web.dao.MedidorDAO{s}getMedidorById(int)
	 */
	@Override
	public Medidor getMedidorById(int medidorId) throws GeaWebException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Medidor> criteria = builder.createQuery(Medidor.class);
		Root<Medidor> root = criteria.from(Medidor.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(Medidor_.medidorId), medidorId));

		List<Medidor> listaMedidores = entityManager.createQuery(criteria).getResultList();

		Medidor medidor = null;
		if (!listaMedidores.isEmpty()) {
			medidor = listaMedidores.iterator().next();
		}
		return medidor;
	}

	/**
	 * 
	 * @see com.gea.web.dao.MedidorDAO{s}listMedidor()
	 */
	@Override
	public List<Medidor> listMedidor() throws GeaWebException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Medidor> criteria = builder.createQuery(Medidor.class);
			Root<Medidor> root = criteria.from(Medidor.class);
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			throw new GeaWebException(e);
		}		
	}
}
