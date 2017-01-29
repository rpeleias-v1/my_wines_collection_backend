package com.rodrigopeleias.minhacolecaovinhos.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Permissao {
	
	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoPermissao tipo;
	
	@ManyToMany(mappedBy = "permissoes", fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPermissao getTipo() {
		return tipo;
	}

	public void setTipo(TipoPermissao tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
