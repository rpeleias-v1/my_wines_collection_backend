package com.rodrigopeleias.mywinecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigopeleias.mywinecollection.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
