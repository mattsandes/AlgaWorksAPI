package com.algaworks.awpag.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.service.ParcelamentoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/parcelamentos/v1")
public class ParcelamentoController {

	private final ParcelamentoRepository repository;
	private final ParcelamentoService service;
	
	@GetMapping
	public List<Parcelamento> listar(){
		return repository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Parcelamento> buscar(@PathVariable Long id){
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Parcelamento cadstrar(@RequestBody Parcelamento parcelamento) {
		return service.cadstrar(parcelamento);
	}
	
	// metodos para tratar exceptions
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> capturarException(NegocioException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
