package com.fatec.sigvs_back.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.sigvs_back.model.Produto;
@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {
	
}