package com.rodrigopeleias.minhacolecaovinhos.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

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
		Vinho vinho = new Vinho();
		vinho.setNome("Casilleero Del Diablo");
		vinho.setAnoSafra(2014);
		vinho.setClassificacao(Classificacao.TINTO);
		vinho.setPaisOrigem("Chile");
		vinho.setFabricante("Concha Y Toro");
		vinho.setUva(Uva.CABERNET_SAUVIGNON);
		vinho.setUsuario(criarUsuario());
		return vinho;
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Rodrigo Peleias");
		usuario.setEmail("rpeleias@hotmail.com");
		usuario.setDataCriacao(new Date());
		usuario.setLogin("rpeleias");
		usuario.setSenha("123");
		
		this.usuarioRepository.save(usuario);
		return usuario;
	}

}
