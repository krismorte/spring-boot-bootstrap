package com.krismorte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krismorte.model.User;
import com.krismorte.secutiry.VerificationToken;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
 
    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);
}
