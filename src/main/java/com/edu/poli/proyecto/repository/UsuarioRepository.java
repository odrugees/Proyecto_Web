package com.edu.poli.proyecto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.edu.poli.proyecto.model.Categoria;
import com.edu.poli.proyecto.model.Usuario;




public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query ("FROM Categoria")
	List<Categoria> findAllCategorias();
	
	@Query ("FROM Usuario WHERE usuario.usuario = ?1")
	Usuario findByusuario(String usuario);


}
