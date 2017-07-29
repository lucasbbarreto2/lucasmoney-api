package com.lucas.lucasmoney.api.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
				LocalDateTime.now().toString(), 
				Arrays.asList(e.getMessage()),
				request.getServletPath(), request.getMethod());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(IdDeCategoriaJaExistenteException.class)
	public ResponseEntity<DetalhesErro> handleIdDeCategoriaJaExistenteException(
			IdDeCategoriaJaExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("409 CONFLITO AO GRAVAR NOVA CATEGORIA COM MESMO CÓDIGO",
				409L, LocalDateTime.now().toString(), 
				Arrays.asList(e.getMessage()),
				request.getServletPath(),request.getMethod());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(
			DataIntegrityViolationException e, HttpServletRequest request){
		
		
		DetalhesErro erro = new DetalhesErro("409 CONFLITO DE INTEGRIDADE DE DADOS",
				409L, LocalDateTime.now().toString(),
				Arrays.asList(e.getMessage()),
				request.getServletPath(), request.getMethod());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErro> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro("404 PARAMETROS INVÁLIDOS",
				404L, LocalDateTime.now().toString(),
				buscaMensagemErro(e.getBindingResult()),
				request.getServletPath(), request.getMethod(), 
				buscaCamposErro(e.getBindingResult()).toArray(new String[0]));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	private List<String> buscaCamposErro(BindingResult bindResult){
		List<String> listaErro = new ArrayList<>();
		
		for(FieldError fieldErro : bindResult.getFieldErrors())
			listaErro.add(fieldErro.getField());
		
		return listaErro;
	}
	
	private List<String> buscaMensagemErro(BindingResult bindResult){
		List<String> listaErro = new ArrayList<>();
		
		for(FieldError fieldErro : bindResult.getFieldErrors())
			listaErro.add(fieldErro.getDefaultMessage());
		
		return listaErro;
	}
	
	private class DetalhesErro{
		private String titulo;
		private Long status;
		private String timestamp;
		private List<String> mensagens;
		private String path;
		private String metodoHttp;
		private String[] camposErro;
		
		public DetalhesErro(String titulo, Long status, String timestamp, List<String> mensagens,
				String path, String metodoHttp, String... campos) {
			this.titulo = titulo;
			this.status = status;
			this.timestamp = timestamp;
			this.mensagens = mensagens;
			this.path = path;
			this.metodoHttp = metodoHttp;
			this.camposErro = campos;
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
		public String getTimestamp() {
			return timestamp;
		}
		@SuppressWarnings("unused")
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		@SuppressWarnings("unused")
		public List<String> getMensagens() {
			return mensagens;
		}
		@SuppressWarnings("unused")
		public void setMensagens(List<String> mensagens) {
			this.mensagens = mensagens;
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
		@SuppressWarnings("unused")
		public String[] getCamposErro() {
			return camposErro;
		}
		@SuppressWarnings("unused")
		public void setCamposErro(String[] campos) {
			this.camposErro = campos;
		}
		
		
	}
}
