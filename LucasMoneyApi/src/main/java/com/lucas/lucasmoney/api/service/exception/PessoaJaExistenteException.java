package com.lucas.lucasmoney.api.service.exception;

public class PessoaJaExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3229125297781442128L;

	public PessoaJaExistenteException() {
		super("Pessoa já existente.");
		// TODO Auto-generated constructor stub
	}

	public PessoaJaExistenteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PessoaJaExistenteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
