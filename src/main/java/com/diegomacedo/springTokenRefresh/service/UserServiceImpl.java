package com.diegomacedo.springTokenRefresh.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.diegomacedo.springTokenRefresh.domain.Role;
import com.diegomacedo.springTokenRefresh.domain.User;
import com.diegomacedo.springTokenRefresh.repository.RoleRepository;
import com.diegomacedo.springTokenRefresh.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	 
	
	@Override
	public User saveUser(User user) {
		log.info("Salvando novo usuario {} no banco de dados", user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Salvando novo role {} no banco de dados",role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		log.info("Adicionando role {} no usuario {}",username,roleName);
		
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		
		user.getRoles().add(role);
		
	}

	@Override
	public User getUser(String username) {
		log.info("Pesquisando usuario {}",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Pesquisando usuarios");
		return userRepo.findAll();
	}

}
