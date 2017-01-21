package com.rodrigopeleias.minhacolecaovinhos.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;
import com.rodrigopeleias.minhacolecaovinhos.builder.VinhoBuilder;
import com.rodrigopeleias.minhacolecaovinhos.model.Classificacao;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.model.Uva;
import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;
import com.rodrigopeleias.minhacolecaovinhos.service.VinhoService;

@RunWith(SpringRunner.class)
@WebMvcTest(VinhoController.class)
public class VinhoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VinhoService vinhoService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testDeveCadastrarVinho() throws Exception {
		Vinho vinho = criarVinho();
		given(this.vinhoService.cadastrar(vinho)).willReturn(vinho);
		String vinhoJson = mapper.writeValueAsString(vinho);
		this.mockMvc.perform(post("/vinhos").content(vinhoJson)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(vinhoJson));
	}
	
	@Test
	public void testDeveBuscarTodosVinho() throws Exception {
		Vinho vinho = criarVinho();
		List<Vinho> vinhos = new ArrayList<>();
		vinhos.add(vinho);
		given(this.vinhoService.listarTodos()).willReturn(vinhos);
		String vinhosJson = mapper.writeValueAsString(vinhos);
		this.mockMvc.perform(get("/vinhos")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(vinhosJson));
	}
	
	@Test
	public void testDeveBuscarVinhoPorId() throws Exception {
		Vinho vinho = criarVinho();
		given(this.vinhoService.consultar(1L)).willReturn(vinho);
		String vinhoJson = mapper.writeValueAsString(vinho);
		this.mockMvc.perform(get("/vinhos/" + 1)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(vinhoJson));
	}
	
	@Test
	public void testDeveBuscarVinhosPorLoginUsuario() throws Exception {
		Vinho vinho = criarVinho();
		List<Vinho> vinhos = new ArrayList<>();
		vinhos.add(vinho);
		given(this.vinhoService.listarPorLoginUsuario("rpeleias")).willReturn(vinhos);
		String vinhosJson = mapper.writeValueAsString(vinhos);
		this.mockMvc.perform(get("/vinhos/login/rpeleias")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(vinhosJson));
	}
	
	@Test
	public void testDeveAtualizarVinho() throws Exception {
		Vinho vinho = criarVinho();
		given(this.vinhoService.atualizar(1L, vinho)).willReturn(vinho);
		String vinhoJson = mapper.writeValueAsString(vinho);
		this.mockMvc.perform(put("/vinhos/" + 1).content(vinhoJson)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(vinhoJson));
	}
	
	@Test
	public void testDeveExcluirVinho() throws Exception {
		doNothing().when(vinhoService).excluir(1L);
		this.mockMvc.perform(delete("/vinhos/" + 1)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().string(""));
	}
	
	private Vinho criarVinho() {
		Vinho vinho = new VinhoBuilder()
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
