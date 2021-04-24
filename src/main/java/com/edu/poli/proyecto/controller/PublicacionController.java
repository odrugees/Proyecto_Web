package com.edu.poli.proyecto.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poli.proyecto.model.Categoria;
import com.edu.poli.proyecto.model.Publicacion;
import com.edu.poli.proyecto.model.Usuario;
import com.edu.poli.proyecto.repository.CategoriaRepository;
import com.edu.poli.proyecto.repository.PublicacionRepository;
import com.edu.poli.proyecto.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/publicacion/")
public class PublicacionController {
	
	@Autowired
	PublicacionRepository publicacionRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar")
	public List<Publicacion> listaCategoria(){
		return publicacionRepository.findAll();
	}
	
	@GetMapping("/visualizar/{id}")
	public Publicacion verPublicacion(@RequestBody Integer id){
		Publicacion publicacion = publicacionRepository.findById(id).get();
		
		return publicacion;
	}

	@PostMapping("/crear")
	public Publicacion crearPubicacion(@RequestBody Publicacion publicacion) {
		return publicacionRepository.save(publicacion);
	}
	
	@PutMapping("/modificar/{id}")
	public Publicacion modificarCategoria(@PathVariable Integer id, @RequestBody Publicacion nuevaPublicacion) {
		Publicacion dbCPublicacion = publicacionRepository.findById(id).get();
		
		dbCPublicacion.setPublicacionTexto(nuevaPublicacion.getPublicacionTexto());
		dbCPublicacion.setPublicacionImagen(nuevaPublicacion.getPublicacionImagen());
		dbCPublicacion.setPublicacionTipoImagen(nuevaPublicacion.getPublicacionTipoImagen());
		publicacionRepository.save(dbCPublicacion);
		return dbCPublicacion;
	}
	
	@DeleteMapping("eliminar/{id}")
	public Publicacion eliminarCategoria(@PathVariable Integer id) {
		Publicacion dbPublicacion = publicacionRepository.findById(id).get();
		publicacionRepository.deleteById(id);
		return dbPublicacion;
	}
	
	@PutMapping("/pubicacionCategoria/{idPublicacion}/{idCategoria}")
	public Publicacion asociarpublicacionCategoria(@PathVariable Integer idPublicacion, @PathVariable Integer idCategoria) {
		
		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		publicacion.setCategoria(categoria);
		categoria.getPublicacion().add(publicacion);
		
		publicacionRepository.save(publicacion);
		categoriaRepository.save(categoria);
		
		return publicacion;
	}
	
	@PutMapping("/pubicacionUsuario/{idPublicacion}/{idUsuario}")
	public Publicacion asociarpublicacionUsuario(@PathVariable Integer idPublicacion, @PathVariable Integer idUsuario) {
		
		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		publicacion.setUsuario(usuario);
		usuario.getPublicacion().add(publicacion);
		
		publicacionRepository.save(publicacion);
		usuarioRepository.save(usuario);
		
		return publicacion;
	}
	
	@PutMapping("/pubicacionMeGusta/{idPublicacion}")
	public Publicacion asociarpublicacionMeGusta(@PathVariable Integer idPublicacion) {
		
		Publicacion dbPublicacion = publicacionRepository.findById(idPublicacion).get();
		Integer numeroMegusta = dbPublicacion.getPublicacionMeGusta();
		numeroMegusta = numeroMegusta +1;
		dbPublicacion.setPublicacionMeGusta(numeroMegusta);
		publicacionRepository.save(dbPublicacion);
		return dbPublicacion;
	}
	
	@PutMapping("/pubicacionNoMeGusta/{idPublicacion}")
	public Publicacion asociarpublicacionNoMeGusta(@PathVariable Integer idPublicacion) {
		
		Publicacion dbPublicacion = publicacionRepository.findById(idPublicacion).get();
		Integer numeroNoMegusta = dbPublicacion.getPublicacionNoMeGusta();
		numeroNoMegusta += 1;
		dbPublicacion.setPublicacionNoMeGusta(numeroNoMegusta);
		publicacionRepository.save(dbPublicacion);
		return dbPublicacion;
	}
}
