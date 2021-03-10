package com.activemq_apache_camel.message_receiver;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class MessageReceiver  extends RouteBuilder {

    @Value("${messageQueue}")
    private String messageQueue ;

    @Override
    public void configure() throws Exception {

        from("jms:queue:"+messageQueue).marshal().json(JsonLibrary.Jackson).to("http://localhost:8080/articles");
    }
}
