package com.edu.poli.proyecto.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="publicacion")
public class Publicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publicacionId;
		
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoriaId")
    @JsonIgnore
    private Categoria categoria; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarioId")
    @JsonIgnore
    private Usuario usuario; 
    
	private String publicacionTexto;
	
	private String publicacionTipoImagen;
	
	@Column(length = 1000)
	private byte[] publicacionImagen;
	
	private Integer publicacionMeGusta;

	private Integer publicacionNoMeGusta;
	
	@Temporal(TemporalType.DATE)
	private Date publicacionFecha;
	
	@OneToMany(mappedBy = "publicacion")
    private Set<Comentario> comentario;
	
	public Publicacion() {

	}

	public int getPublicacionId() {
		return publicacionId;
	}

	public void setPublicacionId(int publicacionId) {
		this.publicacionId = publicacionId;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPublicacionTexto() {
		return publicacionTexto;
	}

	public void setPublicacionTexto(String publicacionTexto) {
		this.publicacionTexto = publicacionTexto;
	}

	public String getPublicacionTipoImagen() {
		return publicacionTipoImagen;
	}

	public void setPublicacionTipoImagen(String publicacionTipoImagen) {
		this.publicacionTipoImagen = publicacionTipoImagen;
	}

	public byte[] getPublicacionImagen() {
		return publicacionImagen;
	}

	public void setPublicacionImagen(byte[] publicacionImagen) {
		this.publicacionImagen = publicacionImagen;
	}

	public Integer getPublicacionMeGusta() {
		return publicacionMeGusta;
	}

	public void setPublicacionMeGusta(Integer publicacionMeGusta) {
		this.publicacionMeGusta = publicacionMeGusta;
	}

	public Integer getPublicacionNoMeGusta() {
		return publicacionNoMeGusta;
	}

	public void setPublicacionNoMeGusta(Integer publicacionNoMeGusta) {
		this.publicacionNoMeGusta = publicacionNoMeGusta;
	}

	public Date getPublicacionFecha() {
		return publicacionFecha;
	}

	public void setPublicacionFecha(Date publicacionFecha) {
		this.publicacionFecha = publicacionFecha;
	}

	public Set<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(Set<Comentario> comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Publicacion [publicacionId=" + publicacionId + ", categoria=" + categoria + ", usuario=" + usuario
				+ ", publicacionTexto=" + publicacionTexto + ", publicacionTipoImagen=" + publicacionTipoImagen
				+ ", publicacionImagen=" + Arrays.toString(publicacionImagen) + ", publicacionMeGusta="
				+ publicacionMeGusta + ", publicacionNoMeGusta=" + publicacionNoMeGusta + ", publicacionFecha="
				+ publicacionFecha + ", comentario=" + comentario + "]";
	}

}
