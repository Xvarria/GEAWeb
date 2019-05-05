package com.gea.web.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MEDIDOR")
public class Medidor {

	private int medidorId;
	private Date fechaInclusion;
	private float ultimaLectura;
	private Date fechaUltimaLectura;
	private List<Lectura> lecutras;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMedidorId() {
		return medidorId;
	}

	public void setMedidorId(int medidorId) {
		this.medidorId = medidorId;
	}

	public Date getFechaInclusion() {
		return fechaInclusion;
	}

	public void setFechaInclusion(Date fechaInclusion) {
		this.fechaInclusion = fechaInclusion;
	}

	public float getUltimaLectura() {
		return ultimaLectura;
	}

	public void setUltimaLectura(float ultimaLectura) {
		this.ultimaLectura = ultimaLectura;
	}

	public Date getFechaUltimaLectura() {
		return fechaUltimaLectura;
	}

	public void setFechaUltimaLectura(Date fechaUltimaLectura) {
		this.fechaUltimaLectura = fechaUltimaLectura;
	}
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="medidor")  
	public List<Lectura> getLecutras() {
		return lecutras;
	}

	public void setLecutras(List<Lectura> lecutras) {
		this.lecutras = lecutras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaInclusion == null) ? 0 : fechaInclusion.hashCode());
		result = prime * result + ((fechaUltimaLectura == null) ? 0 : fechaUltimaLectura.hashCode());
		result = prime * result + medidorId;
		result = prime * result + Float.floatToIntBits(ultimaLectura);
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
		Medidor other = (Medidor) obj;
		if (fechaInclusion == null) {
			if (other.fechaInclusion != null)
				return false;
		} else if (!fechaInclusion.equals(other.fechaInclusion))
			return false;
		if (fechaUltimaLectura == null) {
			if (other.fechaUltimaLectura != null)
				return false;
		} else if (!fechaUltimaLectura.equals(other.fechaUltimaLectura))
			return false;
		if (medidorId != other.medidorId)
			return false;
		if (Float.floatToIntBits(ultimaLectura) != Float.floatToIntBits(other.ultimaLectura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Medidor [medidorId=");
		builder.append(medidorId);
		builder.append(",\n fechaInclusion=");
		builder.append(fechaInclusion);
		builder.append(",\n ultimaLectura=");
		builder.append(ultimaLectura);
		builder.append(",\n fechaUltimaLectura=");
		builder.append(fechaUltimaLectura);
		builder.append("]");
		return builder.toString();
	}	
}
