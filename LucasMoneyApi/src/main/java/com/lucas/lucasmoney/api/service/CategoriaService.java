package com.lucas.lucasmoney.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.lucasmoney.api.model.Categoria;
import com.lucas.lucasmoney.api.repository.CategoriaRepository;
import com.lucas.lucasmoney.api.service.exception.CategoriaNaoEncontradaException;
import com.lucas.lucasmoney.api.service.exception.IdDeCategoriaJaExistenteException;

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
		if(buscarCategoria(categoria.getId())!=null)
			throw new IdDeCategoriaJaExistenteException();
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria buscarCategoria(Long cat_id) throws CategoriaNaoEncontradaException{
		if(cat_id == null)
			throw new CategoriaNaoEncontradaException();
		Categoria categoria = categoriaRepository.findOne(cat_id);
		if(categoria == null)
			throw new CategoriaNaoEncontradaException();
		return categoria;
	}

	@Override
	public Categoria atualizarCategoria(Categoria categoria) {
		if(categoria.getId() != null && buscarCategoria(categoria.getId()) != null)
			return categoriaRepository.save(categoria);
		throw new CategoriaNaoEncontradaException();
	}
	
	@Override
	public void deletarCategoria(Categoria categoria) {
		try{
			Categoria obj = buscarCategoria(categoria.getId());
			categoriaRepository.delete(obj);
		}catch (CategoriaNaoEncontradaException e) {
			Categoria obj = buscarCategoria(categoria.getNome());
			categoriaRepository.delete(obj);
		}
	}


	private Categoria buscarCategoria(String nome) throws CategoriaNaoEncontradaException{
		Categoria categoria = categoriaRepository.findOneByNome(nome);
		if(categoria != null)
			return categoria;
		else
			throw new CategoriaNaoEncontradaException();
	}
	
	
}
