package com.rodrigopeleias.minhacolecaovinhos.service;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;

public interface UsuarioService {

	Usuario save(Usuario usuario);

	Usuario findOne(Long id);

	void delete(Long id);

}