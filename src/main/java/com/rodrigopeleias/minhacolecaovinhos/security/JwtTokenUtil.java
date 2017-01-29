package com.rodrigopeleias.minhacolecaovinhos.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = -2401649531706139886L;

	@Value("${jwt.chaveSecreta}")
	private String chaveSecreta;
	
	@Value("${jwt.expiracao}")
	private Long expiracao;
	
	public String getUsuarioFromToken(String token) {
		String usuario;
		try {
			final Claims claims = getClaimsFromToken(token);
			usuario = claims.getSubject();
		} catch (Exception e) {
			usuario = null;
		}
		return usuario;
	}
	
	public Date getDataCriacaoFromToken(String token) {
		Date dataCriacao;
		try {
			final Claims claims = getClaimsFromToken(token);
			dataCriacao = new Date((Long)claims.get("dataCriacao"));
		} catch (Exception e) {
			dataCriacao = null;
		}
		return dataCriacao;
	}
	
	public Date getDataExpiracaoFromToken(String token) {
		Date dataExpiracao;
		try {
			final Claims claims = getClaimsFromToken(token);
			dataExpiracao = claims.getExpiration();
		} catch (Exception e) {
			dataExpiracao = null;
		}
		return dataExpiracao;
	}
	
	public String getAudienciaFromToken(String token) {
		String audiencia;
		try {
			final Claims claims = getClaimsFromToken(token);
			audiencia = (String)claims.get("audience");
		} catch (Exception e) {
			audiencia = null;
		}
		return audiencia;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(chaveSecreta)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	public String gerarToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("usuario", userDetails.getUsername());
		claims.put("audience", "web");
		claims.put("dataCriacao", new Date());
		return gerarToken(claims);
	}

	private String gerarToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS256, chaveSecreta)
				.setHeaderParam("typ", "JWT")
				.compact();
	}

	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiracao * 1000);
	}
	
	public String atualizarToken(String token) {
		String tokenAtualizado;
		try {
			final Claims claims = getClaimsFromToken(token);
			tokenAtualizado = gerarToken(claims);
		} catch (Exception e) {
			tokenAtualizado = null;
		}
		return tokenAtualizado;
	}
	
	public boolean validarToken(String token, UserDetails userDetails) {
		JwtUsuario usuario = (JwtUsuario)userDetails;
		String login = getUsuarioFromToken(token);
		return (login.equals(usuario.getUsername()) && tokenExpirado(token));
	}
	
	private boolean tokenExpirado(String token) {
        final Date expiration = getDataCriacaoFromToken(token);
        return expiration.before(new Date());
    }
}
