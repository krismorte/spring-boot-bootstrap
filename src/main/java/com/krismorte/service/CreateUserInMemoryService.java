package com.krismorte.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.krismorte.dao.ProfileDao;
import com.krismorte.dao.RoleDao;
import com.krismorte.dao.UserDao;
import com.krismorte.model.Profile;
import com.krismorte.model.Role;
import com.krismorte.model.User;

@Service
public class CreateUserInMemoryService {

	@Autowired
    private UserDao userDao; 
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private RoleDao roleDao;
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	
	public void run() {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		Role roleUSer = new Role();
		roleUSer.setName("ROLE_USER");
		roleDao.save(roleAdmin);
		roleDao.save(roleUSer);
		
		Profile profileAdmin = new Profile();
		profileAdmin.setName("Admin");
		profileAdmin.setRoles(Arrays.asList(roleAdmin));
		
		Profile profileUser = new Profile();
		profileUser.setName("User");
		profileUser.setRoles(Arrays.asList(roleUSer));
		
		profileDao.save(profileAdmin);
		profileDao.save(profileUser);
		
		User user = new User();
		
		user.setName("user");
		user.setLogin("krismorte@yahoo.com.br");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setProfiles(Arrays.asList(profileAdmin,profileUser));
		
		userDao.save(user);		
	}
	
}
