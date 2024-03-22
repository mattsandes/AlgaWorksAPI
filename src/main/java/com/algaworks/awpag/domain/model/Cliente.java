package com.algaworks.awpag.domain.model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable{

	/*a classe de dominio, tambem pode conter logicas de negocios em alguns casos*/
	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank //não permite que se insira um nome vazio;
	@Size(max = 60)
	private String nome;
	
	@Size(max = 255)
	@Email //se houver algo de diferencie de um email, ele estoura uma exceção e
	//nao persiste o recurso;
	private String email;
	
	@NotBlank
	private String telefone;
}
