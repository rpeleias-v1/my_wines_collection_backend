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

import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;

public class UsuarioTest {

	private Validator validator;
	private UsuarioBuilder usuarioBuilder;

	@Before
	public void setupValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		usuarioBuilder = new UsuarioBuilder();
	}

	@Test
	public void testTodosCamposValidos() {
		Usuario usuario = usuarioBuilder
			.comId(1L)
			.comNome("Rodrigo Peleias")
			.comSenha("123")
			.comLogin("rpeleias")
			.comDataCriacao(new Date())
			.comEmail("rpeleias@hotmail.com")
			.build();

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(0, violacoesValidacao.size());
	}

	@Test
	public void testDeveValidarNomeNulo() {
		Usuario usuario = usuarioBuilder
				.comId(1L)
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();
		
		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarLoginNulo() {
		Usuario usuario = usuarioBuilder
				.comId(1L)
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Login do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarEmailNulo() {
		Usuario usuario = usuarioBuilder
				.comId(1L)
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.build();

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("E-mail do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarEmailInvalido() {
		Usuario usuario = usuarioBuilder
				.comId(1L)
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("errado")
				.build();

		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Formato de e-mail informado está inválido", violacoesValidacao.iterator().next().getMessage());
	}

	@Test
	public void testDeveValidarSenhaObrigatoria() {
		Usuario usuario = usuarioBuilder
				.comId(1L)
				.comNome("Rodrigo Peleias")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();
		Set<ConstraintViolation<Usuario>> violacoesValidacao = validator.validate(usuario);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Senha do usuário é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}

}
