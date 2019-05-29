package com.gea.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gea.web.service.impl.UtilBO;

@Entity
@Table(name = "LECTURA")
public class Lectura {

	private int lecturaId;
	private Medidor medidor;
	private float temperatura;
	private Date fechaLectura;
	private String error;
	private float volumen;
	private boolean procesada;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LECTURA_ID")
	public int getLecturaId() {
		return lecturaId;
	}

	public void setLecturaId(int lecturaId) {
		this.lecturaId = lecturaId;
	}

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MEDIDOR_ID")
	public Medidor getMedidor() {
		return medidor;
	}

	public void setMedidor(Medidor medidor) {
		this.medidor = medidor;
	}

	@Column(name = "TEMPERATURA")
	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	
	@Column(name = "FECHA_LECTURA")
	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	@Column(name = "ERROR")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Column(name = "VOLUMEN")
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
	
	@Transient
	public String getFechaLecturaFmt() {
		return UtilBO.getFechaFmt(this.fechaLectura);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((fechaLectura == null) ? 0 : fechaLectura.hashCode());
		result = prime * result + lecturaId;
		result = prime * result + ((medidor == null) ? 0 : medidor.hashCode());
		result = prime * result + (procesada ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(temperatura);
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
		if (lecturaId != other.lecturaId)
			return false;
		if (medidor == null) {
			if (other.medidor != null)
				return false;
		} else if (!medidor.equals(other.medidor))
			return false;
		if (procesada != other.procesada)
			return false;
		if (Float.floatToIntBits(temperatura) != Float.floatToIntBits(other.temperatura))
			return false;
		if (Float.floatToIntBits(volumen) != Float.floatToIntBits(other.volumen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lectura [lecturaId=");
		builder.append(lecturaId);
		builder.append(",/n medidor=");
		builder.append(medidor);
		builder.append(",/n temperatura=");
		builder.append(temperatura);
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
