package com.algaworks.awpag.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.awpag.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//convenção de meotodos customizados para interfaces;
	//findByExpressao_De_Consulta;
	//seguindo essa conveção, o spring JPA, ja entende que o que retornar;
	public List<Cliente> findByNome(String name);
	Optional<Cliente> findByEmail(String email);
}
