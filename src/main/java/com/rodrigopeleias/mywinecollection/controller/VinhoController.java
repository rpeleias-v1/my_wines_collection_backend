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

import com.rodrigopeleias.mywinecollection.model.Vinho;
import com.rodrigopeleias.mywinecollection.service.VinhoService;

@RestController
@RequestMapping("/vinhos")
public class VinhoController {

	private VinhoService vinhoService;

	@Autowired
	public VinhoController(VinhoService vinhoService) {
		this.vinhoService = vinhoService;
	}

	@PostMapping
	public Vinho save(@RequestBody Vinho vinho) {
		return vinhoService.save(vinho);
	}

	@GetMapping
	public List<Vinho> findAll() {
		return vinhoService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Vinho findOne(@PathVariable Long id) {
		return vinhoService.findOne(id);
	}
	
	@PutMapping(value = "/{id}")
	public Vinho update(@PathVariable Long id, @RequestBody Vinho vinho) {
		return vinhoService.save(vinho);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		vinhoService.delete(id);
	}
}