package com.lucas.lucasmoney.api.service;

import java.util.List;

import com.lucas.lucasmoney.api.model.Categoria;

public interface CategoriaServiceInterface {
	
	List<Categoria> buscarTodos();
	Categoria salvarCategoria(Categoria categoria);
	Categoria buscarCategoria(Long cat_id);
}
