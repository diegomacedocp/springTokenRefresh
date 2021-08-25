package com.diegomacedo.springTokenRefresh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diegomacedo.springTokenRefresh.domain.Role;
import com.diegomacedo.springTokenRefresh.domain.User;
import com.diegomacedo.springTokenRefresh.repository.RoleRepository;
import com.diegomacedo.springTokenRefresh.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			log.error("Usuário não encontrado no banco");
			throw new UsernameNotFoundException("Usuário não encontrado no banco");
		} else {
			log.info("Usuário encontrado no banco {}", username);
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public User saveUser(User user) {
		log.info("Salvando novo usuario {} no banco de dados", user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Salvando novo role {} no banco de dados", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {

		log.info("Adicionando role {} no usuario {}", username, roleName);

		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);

		user.getRoles().add(role);

	}

	@Override
	public User getUser(String username) {
		log.info("Pesquisando usuario {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Pesquisando usuarios");
		return userRepo.findAll();
	}

}
