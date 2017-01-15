package com.rodrigopeleias.minhacolecaovinhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigopeleias.minhacolecaovinhos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
