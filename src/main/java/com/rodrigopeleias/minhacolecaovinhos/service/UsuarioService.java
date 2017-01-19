package com.rodrigopeleias.minhacolecaovinhos.service;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;

public interface UsuarioService {

	Usuario cadastrar(Usuario usuario);

	Usuario consultar(Long id);

	void excluir(Long id);

}