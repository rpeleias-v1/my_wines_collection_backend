package com.rodrigopeleias.minhacolecaovinhos.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Validator validator;

	@Before
	public void setupValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testTodosCamposValidos() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Rodrigo Peleias");
		usuario.setLogin("rpeleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setSenha("123");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(0, violacoesValidacao.size());
	}

	@Test
	public void testDeveValidarNomeNulo() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("rpeleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setSenha("123");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarLoginNulo() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Rodrigo Peleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setSenha("123");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Login do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarEmailNulo() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Rodrigo Peleias");
		usuario.setLogin("rpeleias");
		usuario.setSenha("123");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("E-mail do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarEmailInvalido() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Rodrigo Peleias");
		usuario.setLogin("rpeleias");
		usuario.setEmail("errado");
		usuario.setSenha("123");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Formato de e-mail informado está inválido", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarSenhaObrigatoria() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Rodrigo Peleias");
		usuario.setLogin("rpeleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setDataCriacao(new Date());

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Senha do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

}
