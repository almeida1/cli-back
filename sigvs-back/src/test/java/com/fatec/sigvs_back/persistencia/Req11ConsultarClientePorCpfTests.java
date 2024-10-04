package com.fatec.sigvs_back.persistencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs_back.model.Cliente;
import com.fatec.sigvs_back.servico.IClienteRepository;
@SpringBootTest
class Req11ConsultarClientePorCpfTests {
	@Autowired
    IClienteRepository repository;
	@Test
    void ct01_consultar_cliente_pelo_cpf_com_sucesso() {
		//********************************************************
        // dado que o cpf esta cadastrado
		//********************************************************
	    String cpf = "57241774043";
	    //********************************************************
        // quando consulto o cliente pelo id
        //********************************************************
		Cliente cliente = repository.findByCpf(cpf);
		//********************************************************
        // entao retorna os detalhes do cliente
        //********************************************************
		assertEquals("Maria Souza", cliente.getNome());
	
	}

}
