/**
 * 
 */
package com.gea.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gea.web.dao.MedidorDAO;
import com.gea.web.model.Medidor;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.service.MedidorBO;

/**
 * @author mchavarria
 *
 */
@Transactional("")
@Service
public class MedidorBOImpl implements MedidorBO {

	@Autowired
	private MedidorDAO medidorDAO;
	
	/**
	 * @see com.gea.web.service.MedidorBO{s}save(com.gea.web.model.Medidor)
	 */
	@Override
	public void save(Medidor medidor) throws GeaWebException {
		this.medidorDAO.save(medidor);
	}

	/**
	 * @see com.gea.web.service.MedidorBO{s}update(com.gea.web.model.Medidor)
	 */
	@Override
	public void update(Medidor medidor) throws GeaWebException {
		this.medidorDAO.update(medidor);
	}

	/**
	 * @see com.gea.web.service.MedidorBO{s}delete(com.gea.web.model.Medidor)
	 */
	@Override
	public void delete(Medidor medidor) throws GeaWebException {
		this.medidorDAO.delete(medidor);
	}

	/**
	 * @see com.gea.web.service.MedidorBO{s}getMedidorById(int)
	 */
	@Override
	public Medidor getMedidorById(int medidorId) throws GeaWebException {
		return this.medidorDAO.getMedidorById(medidorId);
	}

	/**
	 * @see com.gea.web.service.MedidorBO{s}listMedidor()
	 */
	@Override
	public List<Medidor> listMedidor() throws GeaWebException {
		return this.medidorDAO.listMedidor();
	}

}

