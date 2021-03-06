package com.activemq_apache_camel.exception_handler;

import com.activemq_apache_camel.exception.ArticleNotFoundException;
import com.activemq_apache_camel.exception.InvalidInputException;
import com.activemq_apache_camel.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
@RestController
public class ExceptionHandler {



//    @org.springframework.web.bind.annotation.ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseEntity<CustomErrorResponse> handleBadRequest(Exception ex,WebRequest request){
//        CustomErrorResponse errors = new CustomErrorResponse();
//        errors.setError(ex.getMessage());
//        errors.setStatus(HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFound(ArticleNotFoundException ex , WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<CustomErrorResponse> invalidInputHandle(InvalidInputException ex , WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }















}
