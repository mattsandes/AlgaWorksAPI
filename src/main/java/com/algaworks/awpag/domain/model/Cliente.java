package com.algaworks.awpag.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
}
