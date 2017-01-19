package com.rodrigopeleias.minhacolecaovinhos.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void setup() {
		this.usuarioRepository.deleteAll();
	}
	
	@Test
	public void testDeveInserirUsuario() {
		Usuario usuario = criarUsuario();
		Usuario savedUser = this.usuarioRepository.save(usuario);
		
		assertThat(savedUser.getNome(), is(usuario.getNome()));
	}
	
	@Test
	public void testDeveRetornarUsuarioPorId() {
		Usuario usuario = criarUsuario();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario);	
		Usuario usuarioEncontrado = this.usuarioRepository.findOne(usuarioSalvo.getId());
		
		assertThat(usuarioSalvo.getId(), is(usuarioEncontrado.getId()));
	}
	
	@Test
	public void testDeveRetornarTodosUsuarios() {
		Usuario rodrigo1 = criarUsuario();
		this.testEntityManager.persist(rodrigo1);
		
		Usuario rodrigo2 = criarUsuario();
		rodrigo2.setLogin("rpeleias2");
		rodrigo2.setEmail("rodrigo.peleias@gmail.com");
		this.testEntityManager.persist(rodrigo2);
		
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		
		assertFalse(usuarios.isEmpty());
		assertThat(usuarios.size(), is(2));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testDeveRetornarErroAoInserirLoginIgual() {
		Usuario rodrigo1 = criarUsuario();
		this.usuarioRepository.save(rodrigo1);
		
		Usuario rodrigo2 = criarUsuario();
		rodrigo2.setEmail("rodrigo.peleias@gmail.com");
		this.usuarioRepository.save(rodrigo2);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testDeveRetornarErroAoInserirEmailIgual() {
		Usuario rodrigo1 = criarUsuario();
		this.usuarioRepository.save(rodrigo1);
		
		Usuario rodrigo2 = criarUsuario();
		rodrigo2.setLogin("rpeleias2");
		this.usuarioRepository.save(rodrigo2);
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
	
	@Test
	public void testDeveDeletarUsuario() {
		Usuario usuario = criarUsuario();
		this.testEntityManager.persist(usuario);
		
		this.usuarioRepository.delete(usuario.getId());
		
		Usuario usuarioRemovido = this.usuarioRepository.findOne(usuario.getId());
		assertNull(usuarioRemovido);
	}

}
