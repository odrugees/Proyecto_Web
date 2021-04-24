package com.edu.poli.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poli.proyecto.model.Comentario;
import com.edu.poli.proyecto.model.Publicacion;
import com.edu.poli.proyecto.model.Usuario;
import com.edu.poli.proyecto.repository.ComentarioRepository;
import com.edu.poli.proyecto.repository.PublicacionRepository;
import com.edu.poli.proyecto.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/comentario/")
@CrossOrigin(origins = "*")
public class ComentarioController {
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PublicacionRepository publicacionRepository;

	
	@GetMapping("/listar")
	public List<Comentario> listarComentario(){
		return comentarioRepository.findAll();
	}
	
	@GetMapping("/visualizar/{id}")
	public Comentario verComentario(@PathVariable Integer id) {
	Comentario comentario = comentarioRepository.findById(id).get();
	return comentario;
	}
	
	@PostMapping("/crear")
	public Comentario crearComentario(@RequestBody Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	@PutMapping("/modificar/{id}")
	public Comentario modificarComentario(@PathVariable Integer id, @RequestBody Comentario nuevaCategoria) {
		Comentario dbComentario = comentarioRepository.findById(id).get();
		dbComentario.setComentarioTexto(nuevaCategoria.getComentarioTexto());
		comentarioRepository.save(dbComentario);
		return dbComentario;
	}
	
	@DeleteMapping("eliminar/{id}")
	public Comentario eliminarComentario(@PathVariable Integer id) {
		Comentario dbComentario = comentarioRepository.findById(id).get();
		comentarioRepository.deleteById(id);
		return dbComentario;
	}
	
	@PutMapping("/comentarioUsuario/{idComentario}/{idUsuario}")
	public Comentario asociarComentarioUsuario(@PathVariable Integer idComentario, @PathVariable Integer idUsuario) {
		
		Comentario comentario = comentarioRepository.findById(idComentario).get();
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		comentario.setUsuario(usuario);
		usuario.getComentario().add(comentario);
		
		comentarioRepository.save(comentario);
		usuarioRepository.save(usuario);
		
		return comentario;
	}
	
	@PutMapping("/comentarioPubicacion/{idComentario}/{idPublicacion}")
	public Comentario asociarComentarioPublicacion(@PathVariable Integer idComentario, @PathVariable Integer idPublicacion) {
		
		Comentario comentario = comentarioRepository.findById(idComentario).get();
		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		
		comentario.setPublicacion(publicacion);
		publicacion.getComentario().add(comentario);
		
		comentarioRepository.save(comentario);
		publicacionRepository.save(publicacion);
		
		return comentario;
	}
}
