package com.krismorte.validation;
/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
