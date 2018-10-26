package com.krismorte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.krismorte.model.Profile;
import com.krismorte.model.Role;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
public interface ProfileDao extends CrudRepository<Profile, String> {

	@Query("SELECT p.roles FROM Profile p WHERE p.id = :id")
	public List<Role> findAllRolesById(@Param("id") String id);
	
	public Profile findByName(String name);
}
