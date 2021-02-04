package com.example.demo.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:{{inbound.endpoint}}")
                .transacted()
                .log(LoggingLevel.INFO,"recieved message")
                .to("jms:queue:{{outbound.endpoint}}")
                .log(LoggingLevel.INFO,"Message sent")
                .end();
    }
}
