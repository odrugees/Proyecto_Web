package com.edu.poli.proyecto.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "categoria" )
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoriaId;
	
	private String categoriaNombre;

	private String categoriaDescripcion;

	private Boolean categoriaActiva;
	
	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
    private Set<Publicacion> publicacion;
	
    @ManyToMany(mappedBy = "usuarioCategorias")
    @JsonIgnore
    public Set<Usuario> usuario;
    
	public Categoria() {

	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	public String getCategoriaDescripcion() {
		return categoriaDescripcion;
	}

	public void setCategoriaDescripcion(String categoriaDescripcion) {
		this.categoriaDescripcion = categoriaDescripcion;
	}

	public Boolean getCategoriaActiva() {
		return categoriaActiva;
	}

	public void setCategoriaActiva(Boolean categoriaActiva) {
		this.categoriaActiva = categoriaActiva;
	}

	public Set<Publicacion> getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Set<Publicacion> publicacion) {
		this.publicacion = publicacion;
	}

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Categoria [categoriaId=" + categoriaId + ", categoriaNombre=" + categoriaNombre
				+ ", categoriaDescripcion=" + categoriaDescripcion + ", categoriaActiva=" + categoriaActiva
				+ ", publicacion=" + publicacion + ", usuario=" + usuario + "]";
	}



	
}
