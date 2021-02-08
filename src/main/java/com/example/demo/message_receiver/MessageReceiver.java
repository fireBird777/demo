package com.example.demo.message_receiver;

import com.example.demo.controller.ArticleController;
import com.example.demo.model.Article;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;


@Component
public class MessageReceiver  extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void configure() throws Exception {

        //Insert/update Route
        from("jms:queue:IBOUND").process(new Processor() {
            public void process(Exchange xchg) throws Exception {
                //Take the Employee object from the exchange and create the insert query
                Article article = xchg.getIn().getBody(Article.class);

                // Set the insert/update query in body and call camel jdbc
                xchg.getIn().setBody(article);
            }
        }).bean(ArticleController.class,"insertArticle(${body}, true)");
    }
}
