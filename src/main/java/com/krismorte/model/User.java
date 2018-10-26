package com.krismorte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User extends MyEntity {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(length = 70)
	private String login;
	@NotNull
	private String password;
	@NotNull
	@Column(length = 150)
	private String name;
	@ManyToMany
	@JoinTable(name = "user_profiles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "profile_id") })
	private List<Profile> profiles;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean accountNonLocked;

	
	
	/*public User(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}*/


}
