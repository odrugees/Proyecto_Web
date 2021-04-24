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

import com.edu.poli.proyecto.model.Rol;
import com.edu.poli.proyecto.repository.RolRepository;

@RestController
@RequestMapping("/api/rol/")
public class RolController {
	
	@Autowired
	RolRepository rolRepository;
	
	@GetMapping("/listar")
	public List<Rol> listarRol(){
		return rolRepository.findAll();
	}
	
	@GetMapping("/visualizar/{id}")
	public Rol verRol(@PathVariable Integer id) {
	Rol rol = rolRepository.findById(id).get();
	return rol;
	}
	
	@PostMapping("/crear")
	public Rol crearRol(@RequestBody Rol rol) {
		return rolRepository.save(rol);
	}
	
	@PutMapping("/modificar/{id}")
	public Rol modificarRol(@PathVariable Integer id, @RequestBody Rol nuevoRol) {
		Rol dbRol = rolRepository.findById(id).get();
		
		dbRol.setRolNombre(nuevoRol.getRolNombre());
		dbRol.setRolDescripcion(nuevoRol.getRolDescripcion());
		rolRepository.save(dbRol);
		return dbRol;
	}
	
	@DeleteMapping("eliminar/{id}")
	public Rol eliminarRol(@PathVariable Integer id) {
		Rol dbRol = rolRepository.findById(id).get();
		rolRepository.deleteById(id);
		return dbRol;
	}
	
}
