package com.rodrigopeleias.minhacolecaovinhos.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.repository.UsuarioRepository;
import com.rodrigopeleias.minhacolecaovinhos.security.JwtUsuario;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario == null) { 
			throw new UsernameNotFoundException("Usuário não encontrado = " + login);
		}
		return new JwtUsuario(usuario);
	}

}
