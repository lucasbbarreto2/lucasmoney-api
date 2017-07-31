package com.lucas.lucasmoney.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.lucasmoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findAllByOrderByNomeAsc();
	Pessoa findOneByNome(String nome);
	
}
