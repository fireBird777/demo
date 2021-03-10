package com.activemq_apache_camel.exception;

import javax.xml.bind.ValidationException;

public class InvalidInputException extends Exception {
    public InvalidInputException(Exception e)
    {
        super(e.getMessage());
    }
}
