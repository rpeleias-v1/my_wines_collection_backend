package com.rodrigopeleias.mywinecollection.service;

import com.rodrigopeleias.mywinecollection.model.Usuario;

public interface UsuarioService {

	Usuario save(Usuario usuario);

	Usuario findOne(Long id);

	void delete(Long id);

}