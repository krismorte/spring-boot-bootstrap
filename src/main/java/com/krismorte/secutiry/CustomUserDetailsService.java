package com.krismorte.secutiry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.krismorte.dao.ProfileDao;
import com.krismorte.dao.UserDao;
import com.krismorte.model.Profile;
import com.krismorte.model.Role;
import com.krismorte.model.User;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
    private UserDao userDao;
	@Autowired
    private ProfileDao profileDao;

	
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userDao.findByLogin(username);
		  List<Profile> profiles = userDao.findAllProfilesByLogin(username);
		  List<Role> roles = new ArrayList<>();
		  System.out.println("profiles: "+profiles.size());
		  for(Profile profile : profiles) {
			  roles.addAll(profileDao.findAllRolesById(profile.getId()));
		  }
		  System.out.println("Roles : "+roles.size());
		  return new UserDetail(user, profiles, roles);
	  }
	
	
}
