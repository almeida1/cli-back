package com.fatec.sigvs_back.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.fatec.sigvs_back.model.Cliente;
import com.fatec.sigvs_back.servico.IClienteRepository;

@SpringBootTest
class Req09CadastrarClienteTests {
	@Autowired
	IClienteRepository repository;

//	@Test
//	void ct01_cadastrar_ciente_com_sucesso() {
//		// dado que o cpf ja esta cadastrado
//		Cliente cliente1 = new Cliente("67294301093", "Jose da Silva",  "01304-000");
//		// quando confirmo o cadastro do cliente
//		Cliente c = repository.save(cliente1);
//		// entao os detalhes do cliente ficam disponiveis para consulta
//		assertEquals(c.getNome(), "Jose da Silva");
//		
//	}

	@Test
	void ct02_cadastrar_cliente_com_cpf_ja_cadastrado() {
		// dado que o cpf ja esta cadastrado
		String cpf = "83965248073";
		// quando confirmo o cadastro do cliente
		Cliente cliente1 = new Cliente(cpf, "Jose da Silva",  "01304-000");
		// entao retorna cliente ja cadastrado
		try {
			repository.save(cliente1);
		} catch (DataIntegrityViolationException e) {
			String mensagemEsperada = "Unique index or primary key violation";
			assertTrue(e.getMessage().contains(mensagemEsperada));
		}
	}

	@Test
	void ct03_cadastrar_cliente_com_cpf_invalido() {
		// dado que o cpf esta invalido
		String cpf = "8396524807";
		try {
			// quando confirmo o cadastro do cliente
			 new Cliente(cpf, "Jose da Silva",  "01304-000");
			// entao retorna cpf invalido
		} catch (IllegalArgumentException e) {
			String mensagemEsperada = "CPF invalido";
			assertTrue(e.getMessage().contains(mensagemEsperada));
		}
	}
}
