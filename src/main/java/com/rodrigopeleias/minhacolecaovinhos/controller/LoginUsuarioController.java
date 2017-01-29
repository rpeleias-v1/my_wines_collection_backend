package com.rodrigopeleias.minhacolecaovinhos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigopeleias.minhacolecaovinhos.security.JwtAuthenticationResponse;
import com.rodrigopeleias.minhacolecaovinhos.security.JwtRequest;
import com.rodrigopeleias.minhacolecaovinhos.security.JwtTokenUtil;
import com.rodrigopeleias.minhacolecaovinhos.security.JwtUsuario;

@RestController
public class LoginUsuarioController {
	
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping(value = "/login/usuario")
	public JwtUsuario getUsuarioAutenticado(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String loginUsuario = jwtTokenUtil.getUsuarioFromToken(token);
		JwtUsuario usuario = (JwtUsuario)userDetailsService.loadUserByUsername(loginUsuario);
		return usuario;
	}
	
	@PostMapping(value = "/auth")
	public ResponseEntity<?> criarTokenAutenticacao(@RequestBody JwtRequest authenticationRequest) {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsuario(),
						authenticationRequest.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsuario());
		final String token = jwtTokenUtil.gerarToken(userDetails);

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}
}
