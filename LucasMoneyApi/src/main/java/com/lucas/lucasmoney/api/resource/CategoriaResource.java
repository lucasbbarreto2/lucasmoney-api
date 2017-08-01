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
import com.lucas.lucasmoney.api.model.Categoria;
import com.lucas.lucasmoney.api.service.CategoriaServiceInterface;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaServiceInterface categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listarTodos(){
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(categoriaService.buscarTodos());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> salvarCategoria(@Valid @RequestBody Categoria categoria,
			HttpServletResponse response){
		Categoria categoriaCriada = categoriaService.salvarCategoria(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaCriada.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
	}
	
	@RequestMapping(value = "/{cat_id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("cat_id") Long cat_id){
		Categoria categoria = categoriaService.buscarCategoria(cat_id);
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Categoria> atualizarCategoria(@Valid @RequestBody Categoria categoria,
			HttpServletResponse response){
		Categoria categoriaAtualizada = categoriaService.atualizarCategoria(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaAtualizada.getId()));

		return ResponseEntity.status(HttpStatus.OK).body(categoriaAtualizada);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCategoria(@RequestBody Categoria categoria){
		categoriaService.deletarCategoria(categoria);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(value = "/{cat_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable("cat_id") Long cat_id){
		categoriaService.deletarCategoria(new Categoria(cat_id));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
