package com.activemq_apache_camel.exception;

import org.everit.json.schema.ValidationException;

public class InvalidInputException extends Exception {
    public InvalidInputException(String errors, ValidationException e)
    {
        super(e.getMessage()+" "+errors);
    }

}
