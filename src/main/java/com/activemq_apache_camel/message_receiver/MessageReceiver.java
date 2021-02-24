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

        //Insert/update Route
        //way 1
       // from("jms:queue:IBOUND").bean(ArticleController.class,"save(${body}, true)");

        //way 2
        from("jms:queue:"+messageQueue).marshal().json(JsonLibrary.Jackson).to("http://localhost:8080/articles");
    }
}
