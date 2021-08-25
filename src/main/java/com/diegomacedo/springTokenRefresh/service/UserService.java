package com.diegomacedo.springTokenRefresh.service;

import java.util.List;

import com.diegomacedo.springTokenRefresh.domain.Role;
import com.diegomacedo.springTokenRefresh.domain.User;

public interface UserService {
	
	User saveUser(User user);
	Role saveRole(Role role);
	
	void addRoleToUser(String username,String roleName);
	User getUser(String username);
	
	List<User>getUsers();

}
