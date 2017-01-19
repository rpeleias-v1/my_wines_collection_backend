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

public class VinhoTest {

	private Validator validator;

	@Before
	public void setupValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testTodosCamposValidos() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(0, violacoesValidacao.size());
	}
	
	@Test
	public void testDeveValidarNomeNulo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do vinho é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarUvaNula() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Tipo da uva é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarClassificacaoNula() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Classificação do vinho é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarAnoSafraMenorMinimo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(1899);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O ano de safra de um vinho deve ser um valor entre 1900 e 2017", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarAnoSafraMaiorMaximo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2018);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O ano de safra de um vinho deve ser um valor entre 1900 e 2017", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarFabricanteNulo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do fabricante é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarPaisOrigemNulo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setUsuario(criarUsuario());
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O nome do país de origem é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarUsuarioNulo() {
		Vinho vinho = new Vinho();
		vinho.setId(1L);
		vinho.setNome("Casillero Del Diablo");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setAnoSafra(2012);
		vinho.setFabricante("Concha Y Toro");
		vinho.setTeorAlcoolico(4.5F);
		vinho.setPaisOrigem("Chile");
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O vinho não pode ser cadastrado sem um usuário dono", violacoesValidacao.iterator().next().getMessage());
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Rodrigo Peleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setDataCriacao(new Date());
		usuario.setLogin("rpeleias");
		usuario.setSenha("123");
		
		return usuario;
	}

}
