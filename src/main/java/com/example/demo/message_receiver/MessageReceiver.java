package com.example.demo.message_receiver;

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
@Service
public class MessageReceiver  extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    ProducerTemplate producerTemplate;


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @JmsListener(destination = "IBOUND")
    public void messageListner(Article article)
    {
        try{
            LOGGER.info("message is...."+article);
            producerTemplate.requestBody("direct:insert", article,List.class);
        }
        catch(Exception e)
        {e.printStackTrace();}

    }


    @Override
    public void configure() throws Exception {

        //Insert/update Route
        from("direct:insert").process(new Processor() {
            public void process(Exchange xchg) throws Exception {
                //Take the Employee object from the exchange and create the insert query
                Article article = xchg.getIn().getBody(Article.class);

                String query = "INSERT INTO article (article_id,title,short_title,no_of_pages,author_name,author_email_address,is_active,is_published) VALUES ('"
                        +article.getArticleId()+"','"
                        +article.getTitle()+"','"
                        +article.getShortTitle()+"','"
                        +article.getNoOfPages()+"','"
                        +article.getAuthorName()+"','"
                        +article.getAuthorEmailAddress()+"','"
                        +article.isActive()+"','"
                        +article.isPublished()+"') ON CONFLICT (article_id) DO UPDATE SET" +
                        " article_id = excluded.article_id, " +
                        "title = excluded.title," +
                        "short_title=excluded.short_title," +
                        "no_of_pages=excluded.no_of_pages," +
                        "author_name=excluded.author_name," +
                        "author_email_address=excluded.author_email_address," +
                        "is_active=excluded.is_active," +
                        "is_published=excluded.is_published";


                // Set the insert/update query in body and call camel jdbc
                xchg.getIn().setBody(query);
            }
        }).to("jdbc:dataSource");
    }
}
