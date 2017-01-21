package com.rodrigopeleias.minhacolecaovinhos.service;

import java.util.List;

import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

public interface VinhoService {

	Vinho cadastrar(Vinho vinho);

	List<Vinho> listarTodos();

	Vinho consultar(Long id);

	void excluir(Long id);

	Vinho atualizar(Long id, Vinho vinho);

	List<Vinho> listarPorLoginUsuario(String login);

}