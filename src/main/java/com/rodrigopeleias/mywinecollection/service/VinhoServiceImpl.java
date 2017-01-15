package com.rodrigopeleias.mywinecollection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.mywinecollection.model.Vinho;
import com.rodrigopeleias.mywinecollection.repository.VinhoRepository;

@Service
public class VinhoServiceImpl implements VinhoService {

	private VinhoRepository vinhoRepository;

	@Autowired
	public VinhoServiceImpl(VinhoRepository vinhoRepository) {
		this.vinhoRepository = vinhoRepository;
	}

	@Override
	public Vinho save(Vinho vinho) {
		return vinhoRepository.save(vinho);
	}
	
	@Override
	public List<Vinho> findAll() {
		return vinhoRepository.findAll();
	}

	@Override
	public Vinho findOne(Long id) {
		return vinhoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		vinhoRepository.delete(id);
	}
}