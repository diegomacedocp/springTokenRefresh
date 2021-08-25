package com.diegomacedo.springTokenRefresh;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.diegomacedo.springTokenRefresh.domain.Role;
import com.diegomacedo.springTokenRefresh.domain.User;
import com.diegomacedo.springTokenRefresh.service.UserService;

@Configuration
public class SpringTokenRefreshRunner implements ApplicationRunner {

	@Autowired
	private UserService userSerice;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		userSerice.saveRole(new Role(null,"ROLE_USER"));
		userSerice.saveRole(new Role(null,"ROLE_MANAGER"));
		userSerice.saveRole(new Role(null,"ROLE_ADMIN"));
		userSerice.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
		
		userSerice.saveUser(new User(null,"Diego Macedo","diegomacedo","12345", new ArrayList<>()));
		userSerice.saveUser(new User(null,"Will Smih","will","12345", new ArrayList<>()));
		userSerice.saveUser(new User(null,"Jim Carry","jim","12345", new ArrayList<>()));
		userSerice.saveUser(new User(null,"Arnold Schwarzenegger","arnold","12345", new ArrayList<>()));
		
		userSerice.addRoleToUser("diegomacedo", "ROLE_ADMIN");
		userSerice.addRoleToUser("diegomacedo", "ROLE_USER");
		userSerice.addRoleToUser("diegomacedo", "ROLE_SUPER_ADMIN");
		
		userSerice.addRoleToUser("will", "ROLE_USER");
		userSerice.addRoleToUser("will", "ROLE_MANAGER");
		userSerice.addRoleToUser("jim", "ROLE_MANAGER");
		userSerice.addRoleToUser("arnold", "ROLE_ADMIN");
	}
}