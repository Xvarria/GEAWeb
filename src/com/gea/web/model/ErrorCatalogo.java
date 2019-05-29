package com.gea.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERROR_CATALAGO")
public class ErrorCatalogo {

	private int errorCatalogoId;
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ERROR_CATALAGO_ID")
	public int getErrorCatalogoId() {
		return errorCatalogoId;
	}

	public void setErrorCatalogoId(int errorCatalogoId) {
		this.errorCatalogoId = errorCatalogoId;
	}

	public String getDescription() {
		return description;
	}

	@Column
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + errorCatalogoId;
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
		ErrorCatalogo other = (ErrorCatalogo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (errorCatalogoId != other.errorCatalogoId)
			return false;
		return true;
	}
	
	
}
