package com.fatec.sigvs_back.servico;


import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fatec.sigvs_back.model.Cliente;
import com.fatec.sigvs_back.model.Endereco;

@Service
public class ClienteServico implements IClienteServico {
	Logger logger = LogManager.getLogger(ClienteServico.class);
	@Autowired
	IClienteRepository repository;

	@Override
	public List<Cliente> consultaTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Cliente> cadastrar(Cliente cliente) {
		
		cliente.setDataCadastro();
		String endereco = obtemEndereco(cliente.getCep());
		
		if (endereco.equals(null)) {
			return Optional.empty();

		} else {
			logger.info(">>>>>> endereço ok comando save chamado ");
			cliente.setEndereco(endereco);
			return Optional.ofNullable(repository.save(cliente));
		}

	}

	@Override
	public Optional<Cliente> consultarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Cliente> atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void excluir(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double estoqueImobilizado() {
		// TODO Auto-generated method stub
		return null;
	}


	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		logger.info(">>>>>> obtem endereco 2 chamado");
		// Executa a requisição e captura o ResponseEntity
		try {
			ResponseEntity<Endereco> responseEntity = template.exchange(url, HttpMethod.GET, null, Endereco.class, cep);

			// Obtém o código de status HTTP
			HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();

			// Log do código de status HTTP
			logger.info(">>>>>> Código de status HTTP retornado: " + statusCode.value());

			// Obtém o objeto Endereco da resposta
			Endereco endereco = responseEntity.getBody();
			if (endereco != null) {
				logger.info(">>>>>> 5. clienteservico obtem endereco ==> " + endereco.toString());
				return endereco.getLogradouro();
			} else {
				logger.warn(">>>>>> Endereço não encontrado para o CEP: " + cep);
				return null;
			}
		} catch (HttpClientErrorException e) {
			logger.info(">>>>>> erro não esperado - retornado pela api");
			return null;
		}
	}

}