package com.activemq_apache_camel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
public class CustomErrorResponse {
    private String error;
    private int status;
    private LocalDateTime localDateTime;
}
