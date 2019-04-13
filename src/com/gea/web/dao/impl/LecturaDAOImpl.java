package com.gea.web.dao.impl;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.gea.web.controller.LecturaController;
import com.gea.web.dao.AbstractDAO;
import com.gea.web.dao.LecturaDAO;
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
			/*Session session = entityManager.unwrap(Session.class);
			ProcedureCall call = session.createStoredProcedureCall("SP_REGISTRAR_LECUTRA");
			call.registerParameter("P_LECTURA", String.class, ParameterMode.IN).bindValue(lecturaRequest.toString());
			call.getOutputs().getCurrent();*/
			//Query query = this.entityManager..createSQLQuery(SP_REGISTRAR_LECUTRA);
			//query.registerStoredProcedureParameter("P_LECTURA", String.class, ParameterMode.IN);
			//query.setParameter("P_LECTURA", lecturaRequest.toString());
			//List result = query.list();//query.execute();
			
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_REGISTRAR_LECUTRA");
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

}
