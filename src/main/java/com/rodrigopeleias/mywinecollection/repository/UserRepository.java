package com.rodrigopeleias.mywinecollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigopeleias.mywinecollection.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
