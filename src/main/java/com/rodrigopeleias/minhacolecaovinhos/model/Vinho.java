package com.rodrigopeleias.minhacolecaovinhos.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Vinho {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@NotEmpty(message = "Nome do vinho é um campo obrigatório")
	private String nome;

	@NotNull(message = "Tipo da uva é um campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Uva uva;

	@NotNull(message = "Classificação do vinho é um campo obrigatório")
	@Enumerated(EnumType.STRING)
	private Classificacao classificacao;

	@Min(value = 1900, message = "O ano de safra de um vinho deve ser um valor entre 1900 e 2017")
	@Max(value = 2016, message = "O ano de safra de um vinho deve ser um valor entre 1900 e 2017")
	private int anoSafra;

	@NotEmpty(message = "Nome do fabricante é um campo obrigatório")
	private String fabricante;

	private float teorAlcoolico;

	@NotEmpty(message = "O nome do país de origem é um campo obrigatório")
	private String paisOrigem;

	@NotNull(message ="O vinho não pode ser cadastrado sem um usuário dono")
	@ManyToOne
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Uva getUva() {
		return uva;
	}

	public void setUva(Uva uva) {
		this.uva = uva;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public int getAnoSafra() {
		return anoSafra;
	}

	public void setAnoSafra(int anoSafra) {
		this.anoSafra = anoSafra;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(float teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vinho other = (Vinho) obj;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vinho [id=" + id + ", nome=" + nome + ", uva=" + uva + ", classificacao=" + classificacao
				+ ", anoSafra=" + anoSafra + ", fabricante=" + fabricante + ", teorAlcoolico=" + teorAlcoolico
				+ ", paisOrigem=" + paisOrigem + ", usuario=" + usuario + "]";
	}

}
