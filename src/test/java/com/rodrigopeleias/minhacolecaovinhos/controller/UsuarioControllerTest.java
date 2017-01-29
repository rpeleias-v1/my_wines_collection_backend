package com.rodrigopeleias.minhacolecaovinhos.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigopeleias.minhacolecaovinhos.builder.UsuarioBuilder;
import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;
import com.rodrigopeleias.minhacolecaovinhos.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UsuarioService usuarioService; 
	
	@Autowired 
	private ObjectMapper mapper;

	@Test
	public void testDeveSalvarUsuario() throws Exception {
		Usuario usuario = criarUsuario();
		given(this.usuarioService.cadastrar(usuario)).willReturn(usuario);
		String usuarioJson = mapper.writeValueAsString(usuario);
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ROLE_USER";
			}
		});        
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, "teste",list);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		this.mockMvc.perform(post("/usuarios").content(usuarioJson)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(usuarioJson));
	}
	
	@Test
	public void testDeveBuscarUsuarioPorId() throws Exception {
		Usuario usuario = criarUsuario();
		given(this.usuarioService.consultar(1L)).willReturn(usuario);
		String usuarioJson = mapper.writeValueAsString(usuario);
		this.mockMvc.perform(get("/usuarios/" + 1)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().json(usuarioJson));
	}
	
	@Test
	public void testDeveBuscarUsuarioPorIdInvalido() throws Exception {
		this.mockMvc.perform(get("/usuarios/" + 2)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().string(""));
	}
	
	@Test
	public void testDeveExcluirUsuario() throws Exception {
		doNothing().when(usuarioService).excluir(1L);
		this.mockMvc.perform(delete("/usuarios/" + 1)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(content().string(""));
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
