package com.rodrigopeleias.mywinecollection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigopeleias.mywinecollection.model.Wine;
import com.rodrigopeleias.mywinecollection.repository.WineRepository;

@Service
public class WineServiceImpl implements WineService {

	private WineRepository wineRepository;

	@Autowired
	public WineServiceImpl(WineRepository wineRepository) {
		this.wineRepository = wineRepository;
	}

	@Override
	public Wine save(Wine wine) {
		return wineRepository.save(wine);
	}
	
	@Override
	public List<Wine> findAll() {
		return wineRepository.findAll();
	}

	@Override
	public Wine findOne(Long id) {
		return wineRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		wineRepository.delete(id);
	}
}