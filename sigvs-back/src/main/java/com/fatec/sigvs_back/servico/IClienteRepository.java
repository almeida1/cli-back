package com.fatec.sigvs_back.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fatec.sigvs_back.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	public Cliente findByCpf(@Param("cpf") String cpf);
}