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
import com.rodrigopeleias.minhacolecaovinhos.builder.VinhoBuilder;

public class VinhoTest {

	private Validator validator;
	private VinhoBuilder vinhoBuilder;

	@Before
	public void setupValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		vinhoBuilder = new VinhoBuilder();
	}
	
	@Test
	public void testTodosCamposValidos() {
		Vinho vinho = vinhoBuilder
			.comId(1L)
			.comNome("Casilleto Del Diablo")
			.comUva(Uva.CABERNET_SAUVIGNON)
			.comClassificacao(Classificacao.TINTO)
			.comAnoSafra(2012)
			.comFabricante("Concha Y Toro")
			.comTeorAlcoolico(4.5F)
			.comPaisOrigem("Chile")
			.comUsuario(criarUsuario())
			.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(0, violacoesValidacao.size());
	}
	
	@Test
	public void testDeveValidarNomeNulo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do vinho é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarUvaNula() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Tipo da uva é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarClassificacaoNula() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Classificação do vinho é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarAnoSafraMenorMinimo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(1899)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O ano de safra de um vinho deve ser um valor entre 1900 e 2017", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarAnoSafraMaiorMaximo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2018)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O ano de safra de um vinho deve ser um valor entre 1900 e 2017", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarFabricanteNulo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("Nome do fabricante é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarPaisOrigemNulo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comUsuario(criarUsuario())
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O nome do país de origem é um campo obrigatório", violacoesValidacao.iterator().next().getMessage());
	}
	
	@Test
	public void testDeveValidarUsuarioNulo() {
		Vinho vinho = vinhoBuilder
				.comId(1L)
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.build();
		
		Set<ConstraintViolation<Vinho>> violacoesValidacao = validator.validate(vinho);

		assertEquals(1, violacoesValidacao.size());
		assertEquals("O vinho não pode ser cadastrado sem um usuário dono", violacoesValidacao.iterator().next().getMessage());
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.comId(1L)
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();
		
		return usuario;
	}

}
