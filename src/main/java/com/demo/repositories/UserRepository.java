package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//Optional porque pode ser um usuario que será retornado ou não
	Optional<User> findUserByCpf(String cpf);
	
	Optional<User> findUserById(Long id);
}
