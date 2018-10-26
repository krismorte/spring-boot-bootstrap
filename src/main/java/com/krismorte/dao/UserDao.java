package com.krismorte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.krismorte.model.Profile;
import com.krismorte.model.User;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
public interface UserDao extends CrudRepository<User, String> {

	public User findByLogin(String login);

	@Query("SELECT u.profiles FROM User u WHERE u.login = :login")
	public List<Profile> findAllProfilesByLogin(@Param("login") String login);

}
