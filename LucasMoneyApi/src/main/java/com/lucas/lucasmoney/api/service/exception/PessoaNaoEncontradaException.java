package com.lucas.lucasmoney.api.service.exception;

public class PessoaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204574214035860457L;

	public PessoaNaoEncontradaException() {
		super("Pessoa não encontrada");
		// TODO Auto-generated constructor stub
	}

	public PessoaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PessoaNaoEncontradaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
