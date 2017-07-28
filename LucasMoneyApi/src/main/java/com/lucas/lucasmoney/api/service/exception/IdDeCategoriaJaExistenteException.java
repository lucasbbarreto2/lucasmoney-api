package com.lucas.lucasmoney.api.service.exception;

public class IdDeCategoriaJaExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655754668150215687L;

	public IdDeCategoriaJaExistenteException() {
		super("ID de Categoria já encontrado, impossível "
				+ "gravar, utilizar o método PUT para atualizar a categoria ou utilizar outro ID.");
		// TODO Auto-generated constructor stub
	}

	public IdDeCategoriaJaExistenteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IdDeCategoriaJaExistenteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
