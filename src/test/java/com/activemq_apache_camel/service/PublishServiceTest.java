package com.activemq_apache_camel.service;

import com.activemq_apache_camel.model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;


import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PublishServiceTest {

    private Article article;

    @Mock
    private JmsTemplate jmsTemplate;


//TODO
//Figure out why @Autowired is throwing error for Publicservices
@InjectMocks
    private PublishService publishService = new PublishService();

    private  String message;

    @BeforeEach
    void setUp() {

            article = Article.builder()
                .articleId(1)
                .authorEmailAddress("sham@gmail.com")
                .isPublished(true)
                .isActive(true)
                .authorName("sham")
                .noOfPages(100)
                .title("Physics I")
                .shortTitle("phy I").build();
            message = null;



    }

    @AfterEach
    void tearDown() {
        article = null;
    }

    @Test
    void publishArticle_returns_response_status_as_OK() throws Exception{

        doNothing().when(jmsTemplate).convertAndSend(message,article);
        ResponseEntity<String> response = publishService.publishArticle(article);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(jmsTemplate,times(1)).convertAndSend(message,article);
    }

    @Test
    void publishArticle_returns_response_as_INTERNAL_SERVER_ERROR() throws Exception{
        doThrow(RuntimeException.class).when(jmsTemplate).convertAndSend(message,article);
        ResponseEntity<String> response = publishService.publishArticle(article);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        verify(jmsTemplate,times(1)).convertAndSend(message,article);
    }
}