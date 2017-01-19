package com.rodrigopeleias.minhacolecaovinhos.builder;

import com.rodrigopeleias.minhacolecaovinhos.model.Classificacao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.model.Uva;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

public class VinhoBuilder {

	private Long id;
	private String nome;
	private Uva uva;
	private Classificacao classificacao;
	private int anoSafra;
	private String fabricante;
	private float teorAlcoolico;
	private String paisOrigem;
	private Usuario usuario;

	public VinhoBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public VinhoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public VinhoBuilder comUva(Uva uva) {
		this.uva = uva;
		return this;
	}

	public VinhoBuilder comClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
		return this;
	}

	public VinhoBuilder comAnoSafra(int anoSafra) {
		this.anoSafra = anoSafra;
		return this;
	}

	public VinhoBuilder comFabricante(String fabricante) {
		this.fabricante = fabricante;
		return this;
	}

	public VinhoBuilder comTeorAlcoolico(float teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
		return this;
	}

	public VinhoBuilder comPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
		return this;
	}

	public VinhoBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public Vinho build() {
		Vinho vinho = new Vinho();
		vinho.setId(this.id);
		vinho.setNome(this.nome);
		vinho.setAnoSafra(this.anoSafra);
		vinho.setClassificacao(this.classificacao);
		vinho.setFabricante(this.fabricante);
		vinho.setPaisOrigem(this.paisOrigem);
		vinho.setTeorAlcoolico(this.teorAlcoolico);
		vinho.setUsuario(this.usuario);
		vinho.setUva(this.uva);
		return vinho;
	}
}
