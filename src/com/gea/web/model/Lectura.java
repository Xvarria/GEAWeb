package com.gea.web.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LECTURA")
public class Lectura {

	private int categoriaId;
	private Medidor medidor;
	private float termperatura;
	private Date fechaLectura;
	private String error;
	private float volumen;
	private boolean procesada;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

    @ManyToOne
    @JoinColumn(name = "MEDIDOR_ID")
	public Medidor getMedidor() {
		return medidor;
	}

	public void setMedidor(Medidor medidor) {
		this.medidor = medidor;
	}

	public float getTermperatura() {
		return termperatura;
	}

	public void setTermperatura(float termperatura) {
		this.termperatura = termperatura;
	}

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public float getVolumen() {
		return volumen;
	}

	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}

	public boolean isProcesada() {
		return procesada;
	}

	public void setProcesada(boolean procesada) {
		this.procesada = procesada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoriaId;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((fechaLectura == null) ? 0 : fechaLectura.hashCode());
		result = prime * result + ((medidor == null) ? 0 : medidor.hashCode());
		result = prime * result + (procesada ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(termperatura);
		result = prime * result + Float.floatToIntBits(volumen);
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
		Lectura other = (Lectura) obj;
		if (categoriaId != other.categoriaId)
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (fechaLectura == null) {
			if (other.fechaLectura != null)
				return false;
		} else if (!fechaLectura.equals(other.fechaLectura))
			return false;
		if (medidor == null) {
			if (other.medidor != null)
				return false;
		} else if (!medidor.equals(other.medidor))
			return false;
		if (procesada != other.procesada)
			return false;
		if (Float.floatToIntBits(termperatura) != Float.floatToIntBits(other.termperatura))
			return false;
		if (Float.floatToIntBits(volumen) != Float.floatToIntBits(other.volumen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lectura [categoriaId=");
		builder.append(categoriaId);
		builder.append(",/n medidor=");
		builder.append(medidor);
		builder.append(",/n termperatura=");
		builder.append(termperatura);
		builder.append(",/n fechaLectura=");
		builder.append(fechaLectura);
		builder.append(",/n error=");
		builder.append(error);
		builder.append(",/n volumen=");
		builder.append(volumen);
		builder.append(",/n procesada=");
		builder.append(procesada);
		builder.append("]");
		return builder.toString();
	}
}
