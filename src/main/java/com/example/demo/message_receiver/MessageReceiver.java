package com.example.demo.message_receiver;

import com.example.demo.controller.ArticleController;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;



@Component
public class MessageReceiver  extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        //Insert/update Route
        from("jms:queue:IBOUND").process(new Processor() {
            public void process(Exchange xchg) throws Exception {

            }
        }).bean(ArticleController.class,"save(${body}, true)");
    }
}
