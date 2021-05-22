package com.edu.poli.proyecto.model;

public class Autenticacion {
	
	private String mensaje;
	
	public Autenticacion(String mensaje) {
		this.mensaje = mensaje;
		
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return String.format("Autenticacion Clase [mensaje=%s]", mensaje);
	}
	

}
