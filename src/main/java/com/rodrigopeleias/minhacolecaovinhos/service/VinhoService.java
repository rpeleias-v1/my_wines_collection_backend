package com.rodrigopeleias.minhacolecaovinhos.service;

import java.util.List;

import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

public interface VinhoService {

	Vinho save(Vinho vinho);

	List<Vinho> findAll();

	Vinho findOne(Long id);

	void delete(Long id);

}