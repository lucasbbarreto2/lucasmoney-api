package com.lucas.lucasmoney.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucas.lucasmoney.api.model.Pessoa;
import com.lucas.lucasmoney.api.repository.PessoaRepository;
import com.lucas.lucasmoney.api.service.exception.PessoaJaExistenteException;
import com.lucas.lucasmoney.api.service.exception.PessoaNaoEncontradaException;

@Service
public class PessoaService implements PessoaServiceInterface {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> buscarTodos() {
		return pessoaRepository.findAllByOrderByNomeAsc();
	}

	@Override
	public Pessoa salvarPessoa(Pessoa pessoa) {
		try{
			if(buscarPessoa(pessoa.getId())!=null)
				throw new PessoaJaExistenteException("Impossível gravar pessoa com mesmo id");
		}catch(PessoaNaoEncontradaException e){
			return salvar(pessoa);
		}
		return null;
	}

	@Override
	public Pessoa buscarPessoa(Long pessoa_id) {
		if(pessoa_id == null)
			throw new PessoaNaoEncontradaException();
		Pessoa pessoa = pessoaRepository.findOne(pessoa_id);
		if(pessoa == null)
			throw new PessoaNaoEncontradaException();
		return pessoa;
	}

	@Override
	public Pessoa atualizarPessoa(Pessoa pessoa) {
		if(buscarPessoa(pessoa.getId())!=null)
			return salvar(pessoa);
		throw new PessoaNaoEncontradaException();
	}

	@Override
	public void deletarPessoa(Pessoa pessoa) {
		pessoaRepository.delete(buscarPessoa(pessoa.getId()));
	}
	
	private Pessoa salvar(Pessoa pessoa){
		try{
			return pessoaRepository.save(pessoa);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("Sinto muito, algum problema de integridade aconteceu,"
					+ "porfavor, verifique o objeto enviado");
		}
	}

}
