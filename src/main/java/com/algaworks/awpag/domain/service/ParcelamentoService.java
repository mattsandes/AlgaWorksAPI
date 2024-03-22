package com.algaworks.awpag.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ParcelamentoService {


	private final ParcelamentoRepository repository;
	private final ClienteRepository clienteRepository;
	
	@Transactional
	public Parcelamento cadstrar(Parcelamento parcelamento) {
		if(parcelamento.getId() != null) {
			throw new NegocioException("Parcelamento a ser criado não deve possuir "
					+ "um codigo");
		}
		
		Cliente cliente = clienteRepository
				.findById(parcelamento.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		parcelamento.setDataCriacao(LocalDateTime.now());
		parcelamento.setCliente(cliente); //faz com que aparecam as infromações do cliente 
		//quando o cadstramento for feito;
		
		return repository.save(parcelamento);
	}
}
