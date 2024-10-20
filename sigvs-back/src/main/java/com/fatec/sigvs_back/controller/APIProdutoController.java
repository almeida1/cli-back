package com.fatec.sigvs_back.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvs_back.model.Produto;
import com.fatec.sigvs_back.servico.IProdutoServico;
@CrossOrigin("*") // desabilita o cors do spring security
@RestController
@RequestMapping("/api/v1/produtos")
public class APIProdutoController {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private IProdutoServico produtoServico;

	record ProdutoDTO(String descricao, String categoria, String custo, String quantidadeNoEstoque) {
	};

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody ProdutoDTO p) {
		logger.info(">>>>>> apicontroller cadastrar produto iniciado...");
		try {
			Produto produtoNovo = new Produto();
			produtoNovo.setDescricao(p.descricao);
			produtoNovo.setCategoria(p.categoria);
			produtoNovo.setCusto(p.custo);
			produtoNovo.setQuantidadeNoEstoque(p.quantidadeNoEstoque);
			Optional<Produto> produto = produtoServico.cadastrar(produtoNovo);
			if (produto.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(produto.get());
			} else {
				logger.info(">>>>>> api produto controller cadastrar exception =>");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
