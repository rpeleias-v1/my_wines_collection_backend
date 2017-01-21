package com.rodrigopeleias.minhacolecaovinhos.advices.mensagens;

import java.util.ArrayList;
import java.util.List;

public class MessagemErroCustomizada {

	private Integer status;
	private String excecao;
	private String path;
	private List<ConstraintViolationMessage> constraintViolationMessages = new ArrayList<>();

	public MessagemErroCustomizada() {
	}

	public MessagemErroCustomizada(Integer status, String excecao, String path) {
		this.status = status;
		this.excecao = excecao;
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExcecao() {
		return excecao;
	}

	public void setExcecao(String excecao) {
		this.excecao = excecao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<ConstraintViolationMessage> getConstraintViolationMessages() {
		return constraintViolationMessages;
	}

	public void setConstraintViolationMessages(List<ConstraintViolationMessage> constraintViolationMessages) {
		this.constraintViolationMessages = constraintViolationMessages;
	}

	public void addConstraintViolationMessage(ConstraintViolationMessage message) {
		this.constraintViolationMessages.add(message);
	}

}
