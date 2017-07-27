package com.lucas.lucasmoney.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.lucasmoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	List<Categoria> findAllByOrderByNomeAsc();
}
