package com.lucas.lucasmoney.api.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucas.lucasmoney.api.model.DetalhesErro;
import com.lucas.lucasmoney.api.service.exception.CategoriaNaoEncontradaException;
import com.lucas.lucasmoney.api.service.exception.IdDeCategoriaJaExistenteException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleCategoriaNaoEncontradaException(
			CategoriaNaoEncontradaException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("Categoria não pode ser encontrada", 404L,
				System.currentTimeMillis(), "404 NOT FOUND");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(IdDeCategoriaJaExistenteException.class)
	public ResponseEntity<DetalhesErro> handleIdDeCategoriaJaExistenteException(
			IdDeCategoriaJaExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("ID de Categoria já encontrado, impossível gravar, utilizar"
				+ "o método PUT para atualizar a categoria ou utilizar outro ID.", 409L,
				System.currentTimeMillis(), "409 CONFLITO ERRO AO GRAVAR NOVA CATEGORIA COM MESMO CÓDIGO");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
