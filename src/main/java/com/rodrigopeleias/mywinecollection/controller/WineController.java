package com.rodrigopeleias.mywinecollection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigopeleias.mywinecollection.model.Wine;
import com.rodrigopeleias.mywinecollection.service.WineService;

@RestController
@RequestMapping("/wines")
public class WineController {

	private WineService wineService;

	@Autowired
	public WineController(WineService wineService) {
		this.wineService = wineService;
	}

	@PostMapping
	public Wine save(@RequestBody Wine wine) {
		return wineService.save(wine);
	}

	@GetMapping
	public List<Wine> findAll() {
		return wineService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Wine findOne(@PathVariable Long id) {
		return wineService.findOne(id);
	}
	
	@PutMapping(value = "/{id}")
	public Wine update(@PathVariable Long id, @RequestBody Wine wine) {
		return wineService.save(wine);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		wineService.delete(id);
	}
}