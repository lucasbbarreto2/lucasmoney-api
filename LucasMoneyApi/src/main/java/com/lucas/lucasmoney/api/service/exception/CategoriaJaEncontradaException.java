package com.lucas.lucasmoney.api.service.exception;

public class CategoriaJaEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773336808223074029L;

	public CategoriaJaEncontradaException() {
		super("Categoria já existente.");
		// TODO Auto-generated constructor stub
	}

	public CategoriaJaEncontradaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CategoriaJaEncontradaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
