package com.rodrigopeleias.mywinecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigopeleias.mywinecollection.model.Vinho;

public interface VinhoRepository extends JpaRepository<Vinho, Long>{

}
