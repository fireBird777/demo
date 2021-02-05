package com.example.demo.message_receiver;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
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
    ArticleService articleService;
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
            //articleService.saveOrUpdateArticle(article);
            producerTemplate.requestBody("direct:insert", article,List.class);
        }
        catch(Exception e)
        {e.printStackTrace();}

    }


    @Override
    public void configure() throws Exception {

        //Insert Route
        from("direct:insert").process(new Processor() {
            public void process(Exchange xchg) throws Exception {
                //Take the Employee object from the exchange and create the insert query
                Article article = xchg.getIn().getBody(Article.class);
                //String query = "INSERT INTO article(article_id,title)values(8,'ram')";
                System.out.println(article.getTitle());

                String query = "INSERT INTO article(article_id,title,short_title,no_of_pages,author_name,author_email_address,is_active,is_published)values('" + article.getArticleId() + "','"
                        + article.getTitle() +"','"
                        + article.getShortTitle() +"','"
                        + article.getNoOfPages() +"','"
                        + article.getAuthorName() +"','"
                        + article.getAuthorEmailAddress() +"','"
                        + article.isActive() +"','"
                        + article.isPublished() + "')";
                // Set the insert query in body and call camel jdbc
                xchg.getIn().setBody(query);
            }
        }).to("jdbc:dataSource");
    }
}
