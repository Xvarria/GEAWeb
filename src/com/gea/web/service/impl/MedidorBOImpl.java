/**
 * 
 */
package com.gea.web.service.impl;

import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_GET;
import static com.gea.web.model.constant.GeaWebConstants.MEDIDOR_ERROR_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gea.web.dao.MedidorDAO;
import com.gea.web.model.Medidor;
import com.gea.web.model.exception.ControllerException;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.service.MedidorBO;

/**
 * @author mchavarria
 *
 */
@Service
public class MedidorBOImpl implements MedidorBO {

	@Autowired
	private MedidorDAO medidorDAO;
	
	/**
	 * Get Medidor for database and handle it if null
	 * @param medidorIdStr
	 * @return
	 * @throws ControllerException
	 */
	public Medidor getMedidorFormParamater(int id) throws ControllerException  {
		try {
			Medidor medidor = this.getMedidorById(id);
			if (medidor == null) {
				throw new ControllerException(MEDIDOR_ERROR_NOT_FOUND);
			}
			return medidor;
		} catch (GeaWebException e) {
			throw new ControllerException(MEDIDOR_ERROR_GET);
		}
	}
	
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

