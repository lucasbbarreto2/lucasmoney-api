package com.lucas.lucasmoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.lucasmoney.api.model.Pessoa;
import com.lucas.lucasmoney.api.service.PessoaServiceInterface;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaServiceInterface pessoaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listarTodos(){
		
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(pessoaService.buscarTodos());
	}
	
	@RequestMapping(value = "/{pessoa_id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable("pessoa_id") Long pessoa_id){
				
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).
				cacheControl(cache).body(pessoaService.buscarPessoa(pessoa_id));
		
	}
	
	@RequestMapping(value = "/{pessoa_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPessoa(@PathVariable("pessoa_id") Long pessoa_id){
		pessoaService.deletarPessoa(new Pessoa(pessoa_id));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPessoa(@RequestBody Pessoa pessoa){
		pessoaService.deletarPessoa(pessoa);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("{pessoa_id}").buildAndExpand(pessoaSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pessoaSalva);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> ataulizarPessoa(@RequestBody Pessoa pessoa){
		Pessoa pessoaAtual = pessoaService.atualizarPessoa(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("{posseoa_id}").buildAndExpand(pessoaAtual.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pessoaAtual);
	}
}
