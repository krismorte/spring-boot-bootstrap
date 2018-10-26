package com.krismorte.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krismorte.dao.ProfileDao;
import com.krismorte.dao.UserDao;
import com.krismorte.dao.VerificationTokenRepository;
import com.krismorte.model.Profile;
import com.krismorte.model.User;
import com.krismorte.secutiry.UserDto;
import com.krismorte.secutiry.VerificationToken;
import com.krismorte.validation.EmailExistsException;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Service
@Transactional
public class UserService {

	@Autowired
    private UserDao repository; 
	@Autowired
	private ProfileDao profileDao;
	 @Autowired
	    private VerificationTokenRepository tokenRepository;
	
	
	
    public User registerNewUserAccount(UserDto accountDto) 
      throws EmailExistsException {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        if (emailExist(accountDto.getLogin())) {  
            throw new EmailExistsException(
              "There is an account with that email adress: "
              +  accountDto.getLogin());
        }
        

        User user = new User();
        user.setName(accountDto.getName());
        user.setLogin(accountDto.getLogin());
        user.setPassword(enconder.encode(accountDto.getPassword()));
        user.setEnabled(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
                
        Profile userProfile = profileDao.findByName("USER");
        user.setProfiles(Arrays.asList(userProfile));
        
        return repository.save(user); 
        
    }
    private boolean emailExist(String email) {
        User user = repository.findByLogin(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
     
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
     
    public void saveRegisteredUser(User user) {
        repository.save(user);
    }
     
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
