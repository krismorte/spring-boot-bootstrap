package com.krismorte.controll;

import java.util.Calendar;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.krismorte.model.User;
import com.krismorte.secutiry.OnRegistrationCompleteEvent;
import com.krismorte.secutiry.UserDto;
import com.krismorte.secutiry.VerificationToken;
import com.krismorte.service.UserService;
import com.krismorte.validation.EmailExistsException;

@Controller
public class PublicControll {

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@Autowired
	private UserService userService;
	@Autowired
    private MessageSource messages;
	
	@GetMapping("/")
	public ModelAndView get() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("register","user",new UserDto());
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUserAccount
	      (@ModelAttribute("user") @Valid UserDto accountDto, 
	      BindingResult result, WebRequest request, Errors errors) {    
	    User registered = null;
	    if (!result.hasErrors()) {
	        registered = createUserAccount(accountDto, result);
	    }
	    if (registered == null) {
	        result.rejectValue("login", "message.regError");
	    }
	    /*if (result.hasErrors()) {
	        return new ModelAndView("register", "user", accountDto);
	    } 
	    else {
	        return new ModelAndView("index", "user", accountDto);
	    }*/
	    try {
	        String appUrl = request.getContextPath();
	        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
	          (registered, request.getLocale(), appUrl));
	    } catch (Exception me) {
	    	me.printStackTrace();
	        return new ModelAndView("emailError", "user", accountDto);
	    }
	    return new ModelAndView("index" );
	}
	
	private User createUserAccount(UserDto accountDto, BindingResult result) {
	    User registered = null;
	    try {
	        registered = userService.registerNewUserAccount(accountDto);
	    } catch (EmailExistsException e) {
	        return null;
	    }    
	    return registered;
	}

	
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration
	  (WebRequest request, Model model, @RequestParam("token") String token) {
	  
	    Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
	        model.addAttribute("message", messageValue);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setEnabled(true); 
	    userService.saveRegisteredUser(user); 
	    return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
	}
	
}
