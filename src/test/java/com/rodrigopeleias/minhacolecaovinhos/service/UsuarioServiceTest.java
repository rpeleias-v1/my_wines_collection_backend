package com.rodrigopeleias.minhacolecaovinhos.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Test
	public void testDeveCadastrarNovoUsuario() {
		Usuario usuarioEsperado = criarUsuario();
		when(this.usuarioRepository.save(usuarioEsperado)).thenReturn(usuarioEsperado);
		
		Usuario usuarioCadastrado = this.usuarioService.cadastrar(usuarioEsperado);
		
		assertEquals(usuarioCadastrado, usuarioEsperado);
	}
	
	@Test
	public void testDeveConsultarUsuario() {
		Usuario usuarioEsperado = criarUsuario();
		when(this.usuarioRepository.findOne(1L)).thenReturn(usuarioEsperado);
		
		Usuario usuarioCadastrado = this.usuarioService.consultar(1L);
		
		assertEquals(usuarioCadastrado, usuarioEsperado);
	}
	
	@Test
	public void testDeveExcluirUsuario() {
		doNothing().when(this.usuarioRepository).delete(1L);
		
		this.usuarioService.excluir(1L);
		
		verify(this.usuarioRepository, times(1)).delete(1L);
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Rodrigo Peleias")
				.comSenha("123")
				.comLogin("rpeleias")
				.comDataCriacao(new Date())
				.comEmail("rpeleias@hotmail.com")
				.build();
		return usuario;
	}

}
