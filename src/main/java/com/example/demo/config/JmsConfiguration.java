package com.example.demo.config;

//import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfiguration {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setConcurrency("5-10");
        return jmsListenerContainerFactory;
    }


 /*   public JmsTransactionManager createjmsTransactionManager(ConnectionFactory connectionFactory)
    {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }

    public JmsComponent createjmsComponent(ConnectionFactory connectionFactory, JmsTransactionManager jmsTransactionManager)
    {
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory,jmsTransactionManager);
        return  jmsComponent;
    }
*/
}
