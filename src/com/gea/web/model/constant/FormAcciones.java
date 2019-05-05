package com.gea.web.model.constant;

public enum FormAcciones {
	LISTAR,
	CREAR,
	ACTUALIZAR,
	ELIMINAR,
	VER,
	MOSTRAR;
	
	public static FormAcciones getAccionByDesc(String desc){
		if (desc != null) {
			for (FormAcciones accion : FormAcciones.values()) {
				if (accion.toString().equals(desc)) {
					return accion;
				}
			}
		}
		return null;
	}
}
