package com.rodrigopeleias.minhacolecaovinhos.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigopeleias.minhacolecaovinhos.model.Permissao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;

public class JwtUsuario implements UserDetails {

	private static final long serialVersionUID = 5345286884370679006L;

	private Usuario usuario;

	private Long id;
	private String nome;
	private String senha;
	private String email;
	private String login;
	private boolean habilitado;
	private Date dataCriacao;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUsuario() {
		// TODO Auto-generated constructor stub
	}

	public JwtUsuario(Usuario usuario) {
		this.usuario = usuario;
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.email = usuario.getEmail();
		this.login = usuario.getLogin();
		this.dataCriacao = usuario.getDataCriacao();
		this.authorities = mapToGrantedAuthorities(usuario.getPermissoes());
	}
	
	private static List<? extends GrantedAuthority> mapToGrantedAuthorities(Set<Permissao> permissoes) {
		return permissoes.stream()
				.map(permissao -> new SimpleGrantedAuthority(permissao.getTipo().name()))
				.collect(Collectors.toList());
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

}
