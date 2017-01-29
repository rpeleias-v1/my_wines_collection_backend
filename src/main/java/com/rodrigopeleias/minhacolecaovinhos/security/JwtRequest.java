package com.rodrigopeleias.minhacolecaovinhos.security;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = -5359819244843618058L;

	private String usuario;
	private String senha;

	public JwtRequest() {
	}

	public JwtRequest(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
