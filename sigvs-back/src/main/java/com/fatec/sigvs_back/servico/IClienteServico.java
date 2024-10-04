package com.fatec.sigvs_back.servico;

import java.util.List;
import java.util.Optional;

import com.fatec.sigvs_back.model.Cliente;

public interface IClienteServico {
	public List<Cliente> consultaTodos();
	public Optional<Cliente> cadastrar(Cliente cliente);
	public Optional<Cliente> consultarPorId(Long id);
	public Optional<Cliente> atualizar(Long id, Cliente cliente);
	public void excluir(String id);
	public Double estoqueImobilizado();
}