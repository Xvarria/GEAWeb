package com.gea.web.model;

import java.util.Date;

public class ErrorLectura {
	private int errorLecturaId;
	private Date fechaLectura;
	private ErrorCatalogo errorCatalago;
	private String request;
		
	public int getErrorLecturaId() {
		return errorLecturaId;
	}

	public void setErrorLecturaId(int errorLecturaId) {
		this.errorLecturaId = errorLecturaId;
	}

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	public ErrorCatalogo getErrorCatalago() {
		return errorCatalago;
	}

	public void setErrorCatalago(ErrorCatalogo errorCatalago) {
		this.errorCatalago = errorCatalago;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCatalago == null) ? 0 : errorCatalago.hashCode());
		result = prime * result + errorLecturaId;
		result = prime * result + ((fechaLectura == null) ? 0 : fechaLectura.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorLectura other = (ErrorLectura) obj;
		if (errorCatalago == null) {
			if (other.errorCatalago != null)
				return false;
		} else if (!errorCatalago.equals(other.errorCatalago))
			return false;
		if (errorLecturaId != other.errorLecturaId)
			return false;
		if (fechaLectura == null) {
			if (other.fechaLectura != null)
				return false;
		} else if (!fechaLectura.equals(other.fechaLectura))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorLectura [errorLecturaId=");
		builder.append(errorLecturaId);
		builder.append(",\n fechaLectura=");
		builder.append(fechaLectura);
		builder.append(",\n errorCatalago=");
		builder.append(errorCatalago);
		builder.append(",\n request=");
		builder.append(request);
		builder.append("]");
		return builder.toString();
	}

	
}
