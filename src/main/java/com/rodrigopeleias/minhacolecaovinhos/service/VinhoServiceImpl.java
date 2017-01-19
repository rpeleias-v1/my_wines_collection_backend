package com.rodrigopeleias.minhacolecaovinhos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.minhacolecaovinhos.exception.VinhoNaoEncontradoException;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;
import com.rodrigopeleias.minhacolecaovinhos.repository.VinhoRepository;

@Service
public class VinhoServiceImpl implements VinhoService {

	private VinhoRepository vinhoRepository;

	@Autowired
	public VinhoServiceImpl(VinhoRepository vinhoRepository) {
		this.vinhoRepository = vinhoRepository;
	}

	@Override
	public Vinho criar(Vinho vinho) {
		return vinhoRepository.save(vinho);
	}
	
	@Override
	public List<Vinho> listarTodos() {
		return vinhoRepository.findAll();
	}
	
	@Override
	public Vinho atualizar(Long id, Vinho vinho) {
		Vinho vinhoCadastrado = vinhoRepository.findOne(id);
		if(vinhoCadastrado == null) {
			throw new VinhoNaoEncontradoException(id);
		}
		return vinhoRepository.save(vinho);
	}

	@Override
	public Vinho consultar(Long id) {
		return vinhoRepository.findOne(id);
	}

	@Override
	public void excluir(Long id) {
		vinhoRepository.delete(id);
	}
}