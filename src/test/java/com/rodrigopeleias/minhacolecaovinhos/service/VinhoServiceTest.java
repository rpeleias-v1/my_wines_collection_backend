package com.rodrigopeleias.minhacolecaovinhos.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;
import com.rodrigopeleias.minhacolecaovinhos.builder.VinhoBuilder;
import com.rodrigopeleias.minhacolecaovinhos.exception.VinhoNaoEncontradoException;
import com.rodrigopeleias.minhacolecaovinhos.model.Classificacao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.model.Uva;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;
import com.rodrigopeleias.minhacolecaovinhos.repository.VinhoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VinhoServiceTest {
	
	@MockBean
	private VinhoRepository vinhoRepository;
	
	@Autowired
	private VinhoService vinhoService;

	@Test
	public void testDeveCadastrarNovoVinho() {
		Vinho vinhoEsperado = criarVinho();
		
		when(this.vinhoRepository.save(vinhoEsperado)).thenReturn(vinhoEsperado);
		
		Vinho vinhoCadastrado = this.vinhoService.criar(vinhoEsperado);
		assertEquals(vinhoCadastrado, vinhoEsperado);
	}
	
	@Test
	public void testDeveListarTodosVinhos() {
		Vinho vinhoEsperado = criarVinho();
		List<Vinho> vinhosEsperados = new ArrayList<>();
		vinhosEsperados.add(vinhoEsperado);
		
		when(this.vinhoRepository.findAll()).thenReturn(vinhosEsperados);
		
		List<Vinho> vinhos = this.vinhoService.listarTodos();
		assertFalse(vinhos.isEmpty());
		assertEquals(vinhos, vinhosEsperados);
	}
	
	@Test
	public void testDeveConsultarVinhoPorId() {
		Vinho vinhoEsperado = criarVinho();
		
		when(this.vinhoRepository.findOne(1L)).thenReturn(vinhoEsperado);
		
		Vinho vinho = this.vinhoService.consultar(1L);
		assertNotNull(vinho);
		assertEquals(vinho, vinhoEsperado);
	}
	
	@Test
	public void testDeveAtualizarVinho() {
		Vinho vinhoEsperado = criarVinho();
		
		when(this.vinhoRepository.save(vinhoEsperado)).thenReturn(vinhoEsperado);
		when(this.vinhoRepository.findOne(1L)).thenReturn(vinhoEsperado);
		
		Vinho vinhoAtualizado = this.vinhoService.atualizar(1L, vinhoEsperado);
		assertNotNull(vinhoAtualizado);
		assertEquals(vinhoAtualizado, vinhoEsperado);
	}
	
	@Test(expected = VinhoNaoEncontradoException.class)
	public void testDeveLancarExeptionAoAtualizarVinhoNaoEncontrado() {
		Vinho vinhoEsperado = criarVinho();
		
		when(this.vinhoRepository.save(vinhoEsperado)).thenReturn(vinhoEsperado);
		when(this.vinhoRepository.findOne(1L)).thenReturn(null);
		
		this.vinhoService.atualizar(1L, vinhoEsperado);
	}
	
	@Test
	public void testDeveExcluirVinho() {
		doNothing().when(this.vinhoRepository).delete(1L);
		
		this.vinhoService.excluir(1L);
		verify(this.vinhoRepository, times(1)).delete(1L);
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
		return usuario;
	}

}
