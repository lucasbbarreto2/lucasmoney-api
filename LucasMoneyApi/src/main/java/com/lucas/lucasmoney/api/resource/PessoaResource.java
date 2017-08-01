package com.lucas.lucasmoney.api.resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.lucasmoney.api.event.RecursoCriadoEvent;
import com.lucas.lucasmoney.api.model.Pessoa;
import com.lucas.lucasmoney.api.service.PessoaServiceInterface;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaServiceInterface pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
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
	public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa pessoa, 
			HttpServletResponse response){
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> ataulizarPessoa(@Valid @RequestBody Pessoa pessoa, 
			HttpServletResponse response){
		Pessoa pessoaAtual = pessoaService.atualizarPessoa(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaAtual.getId()));
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaAtual);
	}
}
