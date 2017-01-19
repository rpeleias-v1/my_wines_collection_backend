package com.rodrigopeleias.minhacolecaovinhos.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;
import com.rodrigopeleias.minhacolecaovinhos.builder.VinhoBuilder;
import com.rodrigopeleias.minhacolecaovinhos.model.Classificacao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.model.Uva;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VinhoRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VinhoRepository vinhoRepository;
	
	@Test
	public void testDeveInserirVinho() {
		Vinho vinho = criarVinho();
		
		Vinho vinhoSalvo = this.vinhoRepository.save(vinho);
		
		assertNotNull(vinhoSalvo.getId());
		assertEquals(vinhoSalvo.getNome(), vinho.getNome());
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testDeveRetornarErroAoInserirVinhoSemNome() {
		Vinho vinho = criarVinho();
		vinho.setNome(null);
		
		Vinho vinhoSalvo = this.vinhoRepository.save(vinho);
		
		assertNotNull(vinhoSalvo.getId());
		assertEquals(vinhoSalvo.getNome(), vinho.getNome());
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testDeveRetornarErroAoInserirVinhoSemUsuario() {
		Vinho vinho = criarVinho();
		vinho.setUsuario(null);
		
		Vinho vinhoSalvo = this.vinhoRepository.save(vinho);
		
		assertNotNull(vinhoSalvo.getId());
		assertEquals(vinhoSalvo.getNome(), vinho.getNome());
		
	}
	
	@Test
	public void testDeveRetornarVinhoPorLoginUsuario() {
		Vinho vinho = criarVinho();
		
		this.vinhoRepository.save(vinho);
		
		Vinho vinhoPorUsuario = this.vinhoRepository.findByLoginUsuario(vinho.getUsuario().getLogin());
		
		assertNotNull(vinhoPorUsuario);
		assertEquals(vinhoPorUsuario.getUsuario().getLogin(), vinho.getUsuario().getLogin());
		
	}

	private Vinho criarVinho() {
		Vinho vinho = new VinhoBuilder()
				.comNome("Casilleto Del Diablo")
				.comUva(Uva.CABERNET_SAUVIGNON)
				.comClassificacao(Classificacao.TINTO)
				.comAnoSafra(2012)
				.comFabricante("Concha Y Toro")
				.comTeorAlcoolico(4.5F)
				.comPaisOrigem("Chile")
				.comUsuario(criarUsuario())
				.build();
		return vinho;
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();
		
		this.usuarioRepository.save(usuario);
		return usuario;
	}

}
