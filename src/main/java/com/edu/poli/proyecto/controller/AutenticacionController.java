package com.edu.poli.proyecto.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poli.proyecto.model.Autenticacion;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autenticacion/")
public class AutenticacionController {
	
	@GetMapping("/autenticar")
	public Autenticacion basicAuth() {
		return new Autenticacion("Se logro autenticar");
		
	}
}
