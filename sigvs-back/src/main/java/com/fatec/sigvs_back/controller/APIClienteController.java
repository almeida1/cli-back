package com.fatec.sigvs_back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvs_back.model.Cliente;
import com.fatec.sigvs_back.servico.IClienteServico;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/clientes")
public class APIClienteController {
	@Autowired
	IClienteServico servico;

	@GetMapping("/all")
	public List<Cliente> getAll() {
		return servico.consultaTodos();
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastraCliente(@RequestBody Cliente cliente) {
		Optional<Cliente> c = servico.cadastrar(cliente);
		if (c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(c.get());
		}
	}
	
	
}
