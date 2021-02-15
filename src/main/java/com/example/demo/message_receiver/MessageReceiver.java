package com.example.demo.message_receiver;

import com.example.demo.controller.ArticleController;
import com.example.demo.model.Article;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;



@Component
public class MessageReceiver  extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        //Insert/update Route
        //way 1
       // from("jms:queue:IBOUND").bean(ArticleController.class,"save(${body}, true)");

        //way 2
        from("jms:queue:IBOUND").marshal().json(JsonLibrary.Jackson).to("http://localhost:8080/SaveArticle");
    }
}
