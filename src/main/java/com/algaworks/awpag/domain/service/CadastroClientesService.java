package com.algaworks.awpag.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;

/*Essa classe ficara responsavel por conter
 * as regras de negocio da api*/
@Service
public class CadastroClientesService {

	private final ClienteRepository repository;
	
	public CadastroClientesService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@Transactional //indica que esse metodo vai ser executado dentro de uma transação;
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = repository.findByEmail(cliente.getEmail())
				.filter(c -> !c.equals(cliente))
				.isPresent();
		
		if(emailEmUso) {
			throw new NegocioException("Ja existe um cliente cadastrado com esse email");
		}
		
		return repository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
