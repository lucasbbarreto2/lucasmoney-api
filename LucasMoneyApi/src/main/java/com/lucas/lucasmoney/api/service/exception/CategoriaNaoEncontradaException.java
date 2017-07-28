package com.lucas.lucasmoney.api.service.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7696973441389066179L;

	public CategoriaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CategoriaNaoEncontradaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CategoriaNaoEncontradaException() {
		super("Categoria não pode ser encontrada");
		// TODO Auto-generated constructor stub
	}
	
	
}
