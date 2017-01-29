package com.rodrigopeleias.minhacolecaovinhos.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rodrigopeleias.minhacolecaovinhos.model.Permissao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

public class UsuarioBuilder {

	private Long id;
	private String nome;
	private String senha;
	private String email;
	private String login;
	private Date dataCriacao;
	private List<Vinho> vinhos;
	private List<Permissao> permissoes;

	public UsuarioBuilder() {
		this.vinhos = new ArrayList<>();
		this.permissoes = new ArrayList<>();
	}

	public UsuarioBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public UsuarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public UsuarioBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public UsuarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}

	public UsuarioBuilder comLogin(String login) {
		this.login = login;
		return this;
	}

	public UsuarioBuilder comDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}

	public UsuarioBuilder comVinho(Vinho vinho) {
		this.vinhos.add(vinho);
		return this;
	}
	
	public UsuarioBuilder comPermissoes(Permissao permissao) {
		this.permissoes.add(permissao);
		return this;
	}

	public Usuario build() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setSenha(this.senha);
		usuario.setLogin(this.login);
		usuario.setEmail(this.email);
		usuario.setDataCriacao(this.dataCriacao);
		usuario.setVinhos(this.vinhos);
		usuario.setPermissoes(this.permissoes);
		return usuario;
	}

}
