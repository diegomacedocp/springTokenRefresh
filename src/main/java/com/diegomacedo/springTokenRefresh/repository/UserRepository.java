package com.diegomacedo.springTokenRefresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegomacedo.springTokenRefresh.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
