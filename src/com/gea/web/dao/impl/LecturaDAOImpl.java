package com.gea.web.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.dao.AbstractDAO;
import com.gea.web.dao.LecturaDAO;
import com.gea.web.model.Lectura;
import com.gea.web.model.exception.GeaWebException;
import com.gea.web.model.response.CompletationCode;
import com.gea.web.model.response.ProcesoResponse;
import com.gea.web.model.response.ReasonCode;

/**
 * Implementación de Acceso a datos para lectura
 * @author mchavarria
 * @since 13/04/2019
 */
@Repository
public class LecturaDAOImpl extends AbstractDAO implements LecturaDAO {


	final static Logger log = Logger.getLogger(LecturaDAOImpl.class);
	
	private static final String SP_REGISTRAR_LECUTRA = "SP_REGISTRAR_LECUTRA";

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.gea.web.dao.LecturaDAO#registraLectura(com.gea.web.model.request.LecturaRequest)
	 */
	public ProcesoResponse registraLectura(JsonNode lecturaRequest) {
		ProcesoResponse response = new ProcesoResponse();
		try {			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SP_REGISTRAR_LECUTRA);
			query.registerStoredProcedureParameter("P_LECTURA", String.class, ParameterMode.IN);
			query.setParameter("P_LECTURA", lecturaRequest.toString());
			query.execute();
			
			response.setCompletationCode(CompletationCode.SUCCESS);
			response.setReasonCode(ReasonCode.SUCCESS);
			//TODO agregar parametro de salida al preceso: Exito / fallo total / fallo parcial /no manejado
			//TODO segun el resultado de setea la respuesta
		} catch (Exception e) {
			response.setCompletationCode(CompletationCode.UNHANDLED_ERROR);
			response.setReasonCode(ReasonCode.ERROR);
			log.error("Error executing store procedure",e);
		}
		return response;
	}

	/**
	 * @see com.gea.web.dao.LecturaDAO#listLectura()
	 */
	@Override
	public List<Lectura> listLectura() throws GeaWebException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lectura> criteria = builder.createQuery(Lectura.class);
		Root<Lectura> root = criteria.from(Lectura.class);
		criteria.select(root);
		//criteria.where(builder.equal(root.get(Lectura_.le), medidorId));

		List<Lectura> listaLecturas = entityManager.createQuery(criteria).getResultList();

		return listaLecturas;
	}
}
