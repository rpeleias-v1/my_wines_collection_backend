package com.rodrigopeleias.minhacolecaovinhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rodrigopeleias.minhacolecaovinhos.model.Vinho;

public interface VinhoRepository extends JpaRepository<Vinho, Long>{
	
	@Query("select v from Vinho v where v.usuario.login = :login")
	Vinho findByLoginUsuario(@Param("login") String login);

}
