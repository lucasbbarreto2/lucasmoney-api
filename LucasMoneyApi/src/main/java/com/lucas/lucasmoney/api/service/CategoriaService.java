package com.lucas.lucasmoney.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.lucasmoney.api.model.Categoria;
import com.lucas.lucasmoney.api.repository.CategoriaRepository;
import com.lucas.lucasmoney.api.service.exception.CategoriaNaoEncontradaException;

@Service
public class CategoriaService implements CategoriaServiceInterface {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAllByOrderByNomeAsc();
	}

	@Override
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria buscarCategoria(Long cat_id) {
		Categoria categoria = categoriaRepository.findOne(cat_id);
		if(categoria == null)
			throw new CategoriaNaoEncontradaException();
		return categoria;
	}
	
	
	
}
