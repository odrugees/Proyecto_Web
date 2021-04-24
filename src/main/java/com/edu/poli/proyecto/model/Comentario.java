package com.edu.poli.proyecto.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comentario")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comentarioId;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pubicacionId")
    @JsonIgnore
    private Publicacion publicacion;
    
    private String comentarioTexto;
    
	@Temporal(TemporalType.DATE)
	private Date cometarioFecha;
    
	public Comentario() {

	}

	public int getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(int comentarioId) {
		this.comentarioId = comentarioId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public String getComentarioTexto() {
		return comentarioTexto;
	}

	public void setComentarioTexto(String comentarioTexto) {
		this.comentarioTexto = comentarioTexto;
	}

	public Date getCometarioFecha() {
		return cometarioFecha;
	}

	public void setCometarioFecha(Date cometarioFecha) {
		this.cometarioFecha = cometarioFecha;
	}

	@Override
	public String toString() {
		return "Comentario [comentarioId=" + comentarioId + ", usuario=" + usuario + ", publicacion=" + publicacion
				+ ", comentarioTexto=" + comentarioTexto + ", cometarioFecha=" + cometarioFecha + "]";
	}
	
}
