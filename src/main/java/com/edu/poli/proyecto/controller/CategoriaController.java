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
import com.edu.poli.proyecto.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categoria/")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/listar")
	public List<Categoria> listaCategoria(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/visualizar/{id}")
	public Categoria verCategoria(@RequestBody Integer id){
		Categoria categoria = categoriaRepository.findById(id).get();
		
		return categoria;
	}

	@PostMapping("/crear")
	public Categoria crearCategoria(@RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@PutMapping("/modificar/{id}")
	public Categoria modificarCategoria(@PathVariable Integer id, @RequestBody Categoria nuevaCategoria) {
		Categoria dbCategoria = categoriaRepository.findById(id).get();
		
		dbCategoria.setCategoriaNombre(nuevaCategoria.getCategoriaNombre());
		dbCategoria.setCategoriaDescripcion(nuevaCategoria.getCategoriaDescripcion());
		dbCategoria.setCategoriaActiva(nuevaCategoria.getCategoriaActiva());
		categoriaRepository.save(dbCategoria);
		return dbCategoria;
	}
	
	@DeleteMapping("eliminar/{id}")
	public Categoria eliminarCategoria(@PathVariable Integer id) {
		Categoria dbCategoria = categoriaRepository.findById(id).get();
		categoriaRepository.deleteById(id);
		return dbCategoria;
	}
}
