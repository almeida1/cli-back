package com.fatec.sigvs_back.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs_back.model.Cliente;
import com.fatec.sigvs_back.servico.IClienteRepository;
@SpringBootTest
class Req12ConsultarClientePorIdTests {
	@Autowired
    IClienteRepository repository;
	@Test
    void ct01_consultar_cliente_pelo_id_com_sucesso() {
		//********************************************************
        // dado que o cliente esta cadastrado
		//********************************************************
	    Long clienteId = 1L;
	    //********************************************************
        // quando consulto o cliente pelo id
        //********************************************************
		Cliente cliente = repository.findById(clienteId).get();
		//********************************************************
        // entao retorna os detalhes do cliente
        //********************************************************
		assertEquals("Jose da Silva", cliente.getNome());
	
	}
	
}
