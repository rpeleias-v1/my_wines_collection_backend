package com.rodrigopeleias.mywinecollection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.mywinecollection.model.Usuario;
import com.rodrigopeleias.mywinecollection.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findOne(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.delete(id);
	}
	
}
