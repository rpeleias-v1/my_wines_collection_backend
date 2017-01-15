package com.rodrigopeleias.minhacolecaovinhos.advices.mensagens;

public class ConstraintViolationMessage {

	private String nome;
	private Object value;
	
	public ConstraintViolationMessage(String nome, Object value) {
		this.nome = nome;
		this.value = value;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ConstaintViolation [nome=" + nome + ", value=" + value + "]";
	}
}
