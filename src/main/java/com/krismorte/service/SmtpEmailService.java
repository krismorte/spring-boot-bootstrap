package com.krismorte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
	public JavaMailSender emailSender;


    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOG.debug("Sending email for: {}", message);
        emailSender.send(message);
        LOG.info("Email sent.");
    }

}
