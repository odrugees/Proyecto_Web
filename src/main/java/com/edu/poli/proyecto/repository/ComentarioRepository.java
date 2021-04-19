package com.edu.poli.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.poli.proyecto.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

}
