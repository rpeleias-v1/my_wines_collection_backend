package com.rodrigopeleias.minhacolecaovinhos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario consultar(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}
	
}
