package com.krismorte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Entity
@Data
public class Profile extends MyEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(length = 70)
	private String name;
	@Column(length = 150)
	private String description;

	@ManyToMany
	@JoinTable(name = "profile_role", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roles;

}
