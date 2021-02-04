package com.example.demo.message_receiver;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    ArticleService articleService;

    @JmsListener(destination = "MESSAGE_QUEUE")
    public void messageListner(Article article)
    {
        try{
            LOGGER.info("message is...."+article);
            articleService.saveOrUpdateArticle(article);
        }
        catch(Exception e)
        {e.printStackTrace();}

    }


}
