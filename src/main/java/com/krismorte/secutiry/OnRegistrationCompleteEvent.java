package com.krismorte.secutiry;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.krismorte.model.User;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    
	private static final long serialVersionUID = 1L;
	private String appUrl;
    private Locale locale;
    private User user;
 
    public OnRegistrationCompleteEvent(
      User user, Locale locale, String appUrl) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
