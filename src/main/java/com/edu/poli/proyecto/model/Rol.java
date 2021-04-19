package com.edu.poli.proyecto.model;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "rol" )
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rolId;

	private String rolNombre;

	private String rolDescripcion;
	
    @ManyToMany(mappedBy = "usuarioRoles")
    public Set<Usuario> usuario;
	
	public Rol() {

	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public String getRolDescripcion() {
		return rolDescripcion;
	}

	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Rol [rolId=" + rolId + ", rolNombre=" + rolNombre + ", rolDescripcion=" + rolDescripcion + ", usuario="
				+ usuario + "]";
	}

	
}

