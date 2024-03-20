package com.algaworks.awpag.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.awpag.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente client1 = new Cliente();

		client1.setId(1L);
		client1.setNome("Mateus Joaquim Sandes");
		client1.setEmail("mateussandes@aidento.com");
		client1.setTelefone("9987-5332");

		Cliente client2 = new Cliente();
		client2.setId(2L);
		client2.setNome("Raquel Sandes");
		client2.setEmail("raquelsandes@aidento.com");
		client2.setTelefone("1234-5678");

		return Arrays.asList(client1, client2);
	}
}
