package com.rodrigopeleias.minhacolecaovinhos.exception;

public class VinhoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = -7178877872001702071L;
	
	public VinhoNaoEncontradoException(Long id) {
		super("Vinho com id informado ão cdastrado no sistema = "+  id);
	}

}
