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

import com.lucas.lucasmoney.api.model.Categoria;
import com.lucas.lucasmoney.api.service.CategoriaServiceInterface;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaServiceInterface categoriaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listarTodos(){
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(categoriaService.buscarTodos());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarCategoria(@RequestBody Categoria categoria){
		Categoria categoriaCriada = categoriaService.salvarCategoria(categoria);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoriaCriada.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "/{cat_id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("cat_id") Long cat_id){
		Categoria categoria = categoriaService.buscarCategoria(cat_id);
		CacheControl cache = CacheControl.maxAge(1, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCategoria(@RequestBody Categoria categoria){
		Categoria categoriaAtualizada = categoriaService.atualizarCategoria(categoria);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(categoriaAtualizada.getId()).toUri();
		return ResponseEntity.created(location).build();
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
