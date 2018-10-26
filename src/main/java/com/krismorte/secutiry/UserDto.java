package com.krismorte.secutiry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.krismorte.validation.PasswordMatches;
import com.krismorte.validation.ValidEmail;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Data
@PasswordMatches
public class UserDto {

	@NotNull
    @NotEmpty
    private String name;
     
	@ValidEmail
    @NotNull
    @NotEmpty
    private String login;
     
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
     
	
}
