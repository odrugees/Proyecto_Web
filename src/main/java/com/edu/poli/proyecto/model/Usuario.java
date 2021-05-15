package com.edu.poli.proyecto.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "usuario" )
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;

	private String usuarioNombre;

	private String usuarioContrasena;
	
	@Temporal(TemporalType.DATE)
	private Date usuarioFechaNacimiento;
	
	private String usuarioCorreo;
	
	private String usuarioInformacion;
	
	private String usuarioIntereses;

	private String usuarioConocimientos;
	
	private boolean usuarioActivo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuarioRoles",
			joinColumns = @JoinColumn(name = "usuarioId"),
			inverseJoinColumns = @JoinColumn(name = "rolId")
			)
	@JsonIgnore
	private Set<Rol> usuarioRoles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuarioCategorias",
			joinColumns = @JoinColumn(name = "usuarioId"),
			inverseJoinColumns = @JoinColumn(name = "categoriaId")
			)
	private Set<Categoria> usuarioCategorias = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
    private Set<Publicacion> publicacion;
	
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
    private Set<Comentario> comentario;
	
	public Usuario() {

	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public String getUsuarioContrasena() {
		return usuarioContrasena;
	}

	public void setUsuarioContrasena(String usuarioContrasena) {
		this.usuarioContrasena = usuarioContrasena;
	}

	public Date getUsuarioFechaNacimiento() {
		return usuarioFechaNacimiento;
	}

	public void setUsuarioFechaNacimiento(Date usuarioFechaNacimiento) {
		this.usuarioFechaNacimiento = usuarioFechaNacimiento;
	}

	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}

	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}

	public String getUsuarioInformacion() {
		return usuarioInformacion;
	}

	public void setUsuarioInformacion(String usuarioInformacion) {
		this.usuarioInformacion = usuarioInformacion;
	}

	public String getUsuarioIntereses() {
		return usuarioIntereses;
	}

	public boolean getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioIntereses(String usuarioIntereses) {
		this.usuarioIntereses = usuarioIntereses;
	}

	public String getUsuarioConocimientos() {
		return usuarioConocimientos;
	}


	public void setUsuarioConocimientos(String usuarioConocimientos) {
		this.usuarioConocimientos = usuarioConocimientos;
	}

	public void setUsuarioActivo(boolean usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public Set<Rol> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<Rol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	public Set<Categoria> getUsuarioCategorias() {
		return usuarioCategorias;
	}

	public void setUsuarioCategorias(Set<Categoria> usuarioCategorias) {
		this.usuarioCategorias = usuarioCategorias;
	}

	public Set<Publicacion> getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Set<Publicacion> publicacion) {
		this.publicacion = publicacion;
	}

	public Set<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(Set<Comentario> comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", usuarioNombre=" + usuarioNombre + ", usuarioContrasena="
				+ usuarioContrasena + ", usuarioFechaNacimiento=" + usuarioFechaNacimiento + ", usuarioCorreo="
				+ usuarioCorreo + ", usuarioInformacion=" + usuarioInformacion + ", usuarioIntereses="
				+ usuarioIntereses + ", usuarioConocimientos=" + usuarioConocimientos + ", usuarioActivo="
				+ usuarioActivo + ", usuarioRoles=" + usuarioRoles + ", usuarioCategorias=" + usuarioCategorias
				+ ", publicacion=" + publicacion + ", comentario=" + comentario + "]";
	}

	
	
	
}
