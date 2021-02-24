package com.activemq_apache_camel.exception_handler;

import com.activemq_apache_camel.exceptions.ArticleNotFound;
import com.activemq_apache_camel.model.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //use @Builder and refactor CustomErrorRespose

    @ExceptionHandler(ArticleNotFound.class)
    public ResponseEntity<CustomErrorResponse> handleNotFound(ArticleNotFound ex , WebRequest request)
    {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }
}
