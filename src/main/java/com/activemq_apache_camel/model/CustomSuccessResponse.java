package com.activemq_apache_camel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class CustomSuccessResponse {
    private final int status = HttpStatus.OK.value();
    private final String message = "Success" ;

}
