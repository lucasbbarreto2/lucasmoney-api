package com.lucas.lucasmoney.api.service;

import java.util.List;

import com.lucas.lucasmoney.api.model.Pessoa;

public interface PessoaServiceInterface {
	
	List<Pessoa> buscarTodos();
	Pessoa salvarPessoa(Pessoa pessoa);
	Pessoa buscarPessoa(Long pessoa_id);
	Pessoa atualizarPessoa(Pessoa pessoa);
	void deletarPessoa(Pessoa pessoa);
}
