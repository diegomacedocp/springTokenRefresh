package com.diegomacedo.springTokenRefresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegomacedo.springTokenRefresh.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
