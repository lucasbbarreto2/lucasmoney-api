package com.lucas.lucasmoney.api.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucas.lucasmoney.api.service.exception.CategoriaNaoEncontradaException;
import com.lucas.lucasmoney.api.service.exception.IdDeCategoriaJaExistenteException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleCategoriaNaoEncontradaException(
			CategoriaNaoEncontradaException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("404 CATEGORIA NÃO ENCONTRADA", 404L,
				System.currentTimeMillis(), e.getMessage(),
				request.getServletPath(), request.getMethod());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(IdDeCategoriaJaExistenteException.class)
	public ResponseEntity<DetalhesErro> handleIdDeCategoriaJaExistenteException(
			IdDeCategoriaJaExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("409 CONFLITO AO GRAVAR NOVA CATEGORIA COM MESMO CÓDIGO",
				409L, System.currentTimeMillis(), e.getMessage(),
				request.getServletPath(),request.getMethod());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(
			DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("409 CONFLITO DE INTEGRIDADE DE DADOS",
				409L, System.currentTimeMillis(),e.getMessage(),
				request.getServletPath(), request.getMethod());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	} 
	
	private class DetalhesErro{
		private String titulo;
		private Long status;
		private Long timestamp;
		private String mensagem;
		private String path;
		private String metodoHttp;
		
		public DetalhesErro(String titulo, Long status, Long timestamp, String mensagem,
				String path, String metodoHttp) {
			this.titulo = titulo;
			this.status = status;
			this.timestamp = timestamp;
			this.mensagem = mensagem;
			this.path = path;
			this.metodoHttp = metodoHttp;
		}
		
		@SuppressWarnings("unused")
		public String getTitulo() {
			return titulo;
		}
		@SuppressWarnings("unused")
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		@SuppressWarnings("unused")
		public Long getStatus() {
			return status;
		}
		@SuppressWarnings("unused")
		public void setStatus(Long status) {
			this.status = status;
		}
		@SuppressWarnings("unused")
		public Long getTimestamp() {
			return timestamp;
		}
		@SuppressWarnings("unused")
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
		@SuppressWarnings("unused")
		public String getMensagem() {
			return mensagem;
		}
		@SuppressWarnings("unused")
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		@SuppressWarnings("unused")
		public String getPath() {
			return path;
		}
		@SuppressWarnings("unused")
		public void setPath(String path) {
			this.path = path;
		}
		@SuppressWarnings("unused")
		public String getMetodoHttp() {
			return metodoHttp;
		}
		@SuppressWarnings("unused")
		public void setMetodoHttp(String metodoHttp) {
			this.metodoHttp = metodoHttp;
		}
		
	}
}
