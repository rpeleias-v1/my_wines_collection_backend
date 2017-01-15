package com.rodrigopeleias.mywinecollection.service;

import java.util.List;

import com.rodrigopeleias.mywinecollection.model.Wine;

public interface WineService {

	Wine save(Wine wine);

	List<Wine> findAll();

	Wine findOne(Long id);

	void delete(Long id);

}