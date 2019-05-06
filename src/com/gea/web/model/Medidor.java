package com.gea.web.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gea.web.service.impl.UtilBO;

@Entity
@Table(name = "MEDIDOR")
public class Medidor {

	private int medidorId;
	private Date fechaInclusion;
	private float ultimaLectura;
	private Date fechaUltimaLectura;
	private List<Lectura> lecturas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDIDOR_ID")
	public int getMedidorId() {
		return medidorId;
	}

	public void setMedidorId(int medidorId) {
		this.medidorId = medidorId;
	}

	@Column(name = "FECHA_INCLUSION")
	public Date getFechaInclusion() {
		return fechaInclusion;
	}

	public void setFechaInclusion(Date fechaInclusion) {
		this.fechaInclusion = fechaInclusion;
	}

	//TODO cambiar nombre de la columna
	@Column(name = "ULTIMA_LECUTRA")
	public float getUltimaLectura() {
		return ultimaLectura;
	}

	public void setUltimaLectura(float ultimaLectura) {
		this.ultimaLectura = ultimaLectura;
	}

	@Column(name = "FECHA_ULTIMA_LECTURA")
	public Date getFechaUltimaLectura() {
		return fechaUltimaLectura;
	}

	public void setFechaUltimaLectura(Date fechaUltimaLectura) {
		this.fechaUltimaLectura = fechaUltimaLectura;
	}
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="medidor", fetch = FetchType.LAZY)  
	public List<Lectura> getLecturas() {
		return lecturas;
	}

	public void setLecturas(List<Lectura> lecturas) {
		this.lecturas = lecturas;
	}
	
	
	@Transient
	public String getFechaInclusionFmt() {
		return UtilBO.getFechaFmt(this.fechaInclusion);
	}
	
	@Transient
	public String getFechaUltimaLecturaFmt() {
		return UtilBO.getFechaFmt(this.fechaUltimaLectura);
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
