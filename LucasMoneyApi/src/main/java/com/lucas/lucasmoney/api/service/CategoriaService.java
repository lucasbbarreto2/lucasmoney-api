package com.lucas.lucasmoney.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.lucasmoney.api.model.Categoria;
import com.lucas.lucasmoney.api.repository.CategoriaRepository;
import com.lucas.lucasmoney.api.service.exception.CategoriaJaExistenteException;
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
		try{
			if(buscarCategoria(categoria.getId())!=null)
				throw new IdDeCategoriaJaExistenteException();
		}catch(CategoriaNaoEncontradaException e){
			try{
				if(buscarCategoria(categoria.getNome())!=null)
					throw new CategoriaJaExistenteException();
			}catch(CategoriaNaoEncontradaException n){
				return categoriaRepository.save(categoria);
			}
		}
		return null;
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
	
	private Categoria buscarCategoria(String nome) throws CategoriaNaoEncontradaException{
		Categoria categoria = categoriaRepository.findOneByNome(nome);
		if(categoria != null)
			return categoria;
		else
			throw new CategoriaNaoEncontradaException();
	}
	
	@Override
	public Categoria atualizarCategoria(Categoria categoria) {
		if(buscarCategoria(categoria.getId())!=null)
			try{
				if(buscarCategoria(categoria.getNome())!=null)
					throw new CategoriaJaExistenteException();
			}catch(CategoriaNaoEncontradaException e){
				return categoriaRepository.save(categoria);
			}
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
	
}
