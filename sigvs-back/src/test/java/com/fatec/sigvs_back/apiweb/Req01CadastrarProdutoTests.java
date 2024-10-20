package com.fatec.sigvs_back.apiweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.fatec.sigvs_back.model.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Req01CadastrarProdutoTests {
	String urlBase = "/api/v1/produtos";
	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void ct01_quando_dados_validos_cadatra_produto_com_sucesso() {
		// ********************************************************************
		// Given - dado que as informacoes sao validas
		// ********************************************************************
		String urlBase = "/api/v1/produtos";
		Produto produto = new Produto("Eletrobomba 110V para Maquina de Lavar e Lava Louças", "maquina de lavar", "51.66", "12");
		// ********************************************************************
		// When - quando confirmo o cadastro do produto
		// ********************************************************************
		ResponseEntity<Produto> response = testRestTemplate.postForEntity(urlBase, produto, Produto.class);
		// ********************************************************************
		// Then - entao retorna os detalhes do produto
		// ********************************************************************
		assertEquals("201 CREATED", response.getStatusCode().toString());
		Produto produtoCadastrado = response.getBody();
		assertNotNull(produtoCadastrado.getId());
		assertEquals("Eletrobomba 110V para Maquina de Lavar e Lava Louças", produtoCadastrado.getDescricao());
	}


}
