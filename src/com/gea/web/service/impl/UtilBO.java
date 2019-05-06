package com.gea.web.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.gea.web.model.exception.ParametroIncorrectoException;

public final class UtilBO {
	
	/**
	 * 
	 * @param valorStr
	 * @return
	 * @throws ParametroIncorrectoException
	 */
	public static int getIntParametro(String valorStr) throws ParametroIncorrectoException{
		try {
			int valorInt = Integer.parseInt(valorStr);
			return valorInt;
		} catch (Exception e) {
			throw new ParametroIncorrectoException();
		}
	};
	
	public static final String getFechaFmt(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(fecha);
	}

	public static final String getFechaFmt(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(fecha);
	}

	
}
