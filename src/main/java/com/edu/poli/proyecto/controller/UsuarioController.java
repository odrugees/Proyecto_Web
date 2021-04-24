package com.edu.poli.proyecto.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.edu.poli.proyecto.model.Rol;
import com.edu.poli.proyecto.model.Usuario;
import com.edu.poli.proyecto.repository.CategoriaRepository;
import com.edu.poli.proyecto.repository.RolRepository;
import com.edu.poli.proyecto.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario/")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping("/listar")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/visualizar/{id}")
	public Usuario verUsuario(@PathVariable Integer id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return usuario;
	}

	@PostMapping("/crear")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/modificar/{id}")
	public Usuario modificarUsuario(@PathVariable Integer id, @RequestBody Usuario nuevoUsuario) {
		Usuario dbUsuario = usuarioRepository.findById(id).get();

		dbUsuario.setUsuarioNombre(nuevoUsuario.getUsuarioNombre());
		dbUsuario.setUsuarioContrasena(nuevoUsuario.getUsuarioContrasena());
		dbUsuario.setUsuarioFechaNacimiento(nuevoUsuario.getUsuarioFechaNacimiento());
		dbUsuario.setUsuarioCorreo(nuevoUsuario.getUsuarioCorreo());
		dbUsuario.setUsuarioInformacion(nuevoUsuario.getUsuarioInformacion());
		dbUsuario.setUsuarioIntereses(nuevoUsuario.getUsuarioIntereses());
		dbUsuario.setUsuarioConocimientos(nuevoUsuario.getUsuarioConocimientos());
		dbUsuario.setUsuarioActivo(nuevoUsuario.getUsuarioActivo());
		usuarioRepository.save(dbUsuario);
		return dbUsuario;

	}
	
	@DeleteMapping("/eliminar/{id}")
	public Usuario eliminarUsuario(@PathVariable Integer id) {
		Usuario dbUsuario = usuarioRepository.findById(id).get();
		usuarioRepository.deleteById(id);
		return dbUsuario;
	}
	
	@PutMapping("/usuarioRol/{idUsuario}/{idRol}")
	public Usuario asociarUsuarioRol(@PathVariable Integer idUsuario, @PathVariable Integer idRol) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Rol rol = rolRepository.findById(idRol).get();
		
		Set<Rol> listaRol = new HashSet<>();
		listaRol = usuario.getUsuarioRoles();
		listaRol.add(rol);
		
		usuario.setUsuarioRoles(listaRol);
		rol.getUsuario().add(usuario);
		
		usuarioRepository.save(usuario);
		rolRepository.save(rol);
		
		return usuario;
	}
	
	@DeleteMapping("/usuarioRol/{idUsuario}/{idRol}")
	public Usuario desasociarUsuarioRol(@PathVariable Integer idUsuario, @PathVariable Integer idRol) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Rol rol = rolRepository.findById(idRol).get();
		
		Set<Rol> listaRol = new HashSet<>();
		listaRol = usuario.getUsuarioRoles();
		listaRol.remove(rol);
		
		usuario.setUsuarioRoles(listaRol);
		rol.getUsuario().add(usuario);
		
		usuarioRepository.save(usuario);
		rolRepository.save(rol);
		
		return usuario;
	}
	
	@PutMapping("/usuarioCategoria/{idUsuario}/{idCategoria}")
	public Usuario asociarUsuarioCategoria(@PathVariable Integer idUsuario, @PathVariable Integer idCategoria) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		Set<Categoria> listaCategoria = new HashSet<>();
		listaCategoria =usuario.getUsuarioCategorias();
		listaCategoria.add(categoria);
		
		usuario.setUsuarioCategorias(listaCategoria);
		categoria.getUsuario().add(usuario);
		
		usuarioRepository.save(usuario);
		categoriaRepository.save(categoria);
		
		return usuario;
	}
	
	@DeleteMapping("/usuarioCategoria/{isUsuario}/{idCategoria}")
	public Usuario desasociarUsuarioCategoria(@PathVariable Integer idUsuario, @PathVariable Integer idCategoria) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		Set<Categoria> listaCategoria = new HashSet<>();
		listaCategoria =usuario.getUsuarioCategorias();
		listaCategoria.remove(categoria);
		
		usuario.setUsuarioCategorias(listaCategoria);
		categoria.getUsuario().add(usuario);
		
		usuarioRepository.save(usuario);
		categoriaRepository.save(categoria);
		
		return usuario;
	}
}
