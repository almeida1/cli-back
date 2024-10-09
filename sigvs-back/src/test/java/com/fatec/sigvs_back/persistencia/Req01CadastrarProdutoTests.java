package com.fatec.sigvs_back.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs_back.model.Produto;
import com.fatec.sigvs_back.servico.IProdutoRepository;
@SpringBootTest
class Req01CadastrarProdutoTests {

	@Autowired
    IProdutoRepository repository;

    @Test
    void ct01CadastrarProdutoComSucesso() {
        Produto produto1 = new Produto("eletrobomba 110v", "máquina de lavar", "22.30", "10");
        repository.save(produto1);
        assertEquals(1, repository.count());
    }
}
