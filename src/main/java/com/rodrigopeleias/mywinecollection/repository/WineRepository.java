package com.rodrigopeleias.mywinecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigopeleias.mywinecollection.model.Wine;

public interface WineRepository extends JpaRepository<Wine, Long>{

}
