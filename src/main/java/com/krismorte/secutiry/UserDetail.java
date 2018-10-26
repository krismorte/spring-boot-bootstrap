package com.krismorte.secutiry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.krismorte.model.Profile;
import com.krismorte.model.Role;
import com.krismorte.model.User;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Data
public class UserDetail implements UserDetails{

	private static final long serialVersionUID = 1L;
	private User user;
	private List<String> profiles;
	private List<String> roles;

	public UserDetail(User user,List<Profile> profiles,List<Role> roles) {
        this.user = user;
        this.profiles = new ArrayList<>();
        this.roles = new ArrayList<>();
        profiles.forEach(p -> 
        	this.profiles.add(p.getName()));
        roles.forEach(r -> 
    		this.roles.add(r.getName()));
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities= new ArrayList<>();
		//authorities.add(new SimpleGrantedAuthority("ADMIN"));
		
		roles.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));
		
		return authorities;
	}
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
	public String getId() {
		return user.getId();
	}
	
	public String getName() {
		return user.getName();
	}
	
	public Date getCreateAt() {
		return user.getCreateAt();
	}
	
	public Date getUpdateAt() {
		return user.getUpdateAt();
	}
	
	public List<String> getProfiles() {
		return profiles;
	}
	
}
