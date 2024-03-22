package com.algaworks.awpag.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import com.algaworks.awpag.domain.service.CadastroClientesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes/v1")
public class ClienteController {

	//melhor pratica para injear dependencias no controller;
	private final ClienteRepository repository;
	private final CadastroClientesService service;
	
	public ClienteController(ClienteRepository repository, CadastroClientesService service) {
		this.repository = repository;
		this.service = service;
	}

	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable(value = "id") Long clienteId) {
		Optional<Cliente> cliente = repository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Cliente adicionar(@RequestBody @Valid Cliente cliente) {
		return service.salvar(cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente, @PathVariable Long id) {
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		
		cliente = service.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		service.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	//metodos para tratar exceptions
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> capturarException(NegocioException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
