package com.lucas.lucasmoney.api.model;

public class DetalhesErro {
	private String titulo;
	private Long status;
	private Long timestamp;
	private String mensagem;
	
	public DetalhesErro(String titulo, Long status, Long timestamp, String mensagem) {
		this.titulo = titulo;
		this.status = status;
		this.timestamp = timestamp;
		this.mensagem = mensagem;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
