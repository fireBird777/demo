package com.example.demo.message_receiver;

import com.example.demo.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(destination = "MESSAGE_QUEUE")
    public void messageListner(Article article)
    {
        try{LOGGER.info("message is...."+article);}
        catch(Exception e)
        {e.printStackTrace();}

    }

}
