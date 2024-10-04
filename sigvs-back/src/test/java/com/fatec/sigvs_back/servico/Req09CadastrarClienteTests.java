package com.fatec.sigvs_back.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs_back.model.Cliente;

@SpringBootTest
class Req09CadastrarClienteTests {
	@Autowired
	ClienteServico servico;

	@Test
	void ct01_cadastrar_ciente_com_sucesso() {
		Cliente cliente1 = new Cliente("81099787033", "Jose da Silva", "04280-130");
		Cliente c = servico.cadastrar(cliente1).get();
		assertEquals(c.getNome(), "Jose da Silva");
		assertEquals(c.getEndereco(), "Rua Frei João");
	}

	@Test
	void ct02_cadastrar_ciente_com_cpf_invalido() {
		// dado que o cpf esta invalido
		String cpf = "6729430109";
		// quando confirmo o cadastro
		try {
			servico.cadastrar(new Cliente(cpf, "Jose da Silva", "04280-130"));
		}
		// entao retorna invalido
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			String mensagemEsperada = "CPF invalido";
			assertTrue(e.getMessage().contains(mensagemEsperada));
		}
	}

	@Test
	void ct03_cadastrar_ciente_cep_invalido() {
		// dado que o cep esta invalido
		String cep = "42180-130";
		// quando confirmo o cadastro
		String endereco = servico.obtemEndereco(cep); // erro true
		// entao retorna endereco nulo (invalido)
		assertNull(endereco);
	}

	@Test
	void ct04_cadastrar_cliente_cep_invalido() {
		String endereco = servico.obtemEndereco("2180-130"); // http 400 verifique a url
		assertNull(endereco);
	}
}
