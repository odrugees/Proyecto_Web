package com.edu.poli.proyecto.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.poli.proyecto.model.Usuario;
import com.edu.poli.proyecto.repository.UsuarioRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usuarioUsuario) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByusuario(usuarioUsuario);

		if (usuario == null)
			throw new UsernameNotFoundException(usuarioUsuario);
 
		return new UserDetailsImpl(usuario);
	}

}