package com.activemq_apache_camel.service;

import com.activemq_apache_camel.exception.InvalidInputException;
import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.schema_validator.SchemaValidator;
import org.everit.json.schema.ValidationException;
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

    private  Article article;

    private ArticleDTO articleDTO;

    private ArticleDTO articleDTOInvalid;

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private SchemaValidator schemaValidator;


//TODO
//Figure out why @Autowired is throwing error for Publicservices
@InjectMocks
    private PublishService publishService = new PublishService();

    private  String message;

    @BeforeEach
    void setUp() {

            articleDTO = ArticleDTO.builder()
                .articleId(1)
                .authorEmailAddress("sham@gmail.com")
                .isPublished(true)
                .isActive(true)
                .authorName("sham")
                .noOfPages(100)
                .title("Physics I")
                .shortTitle("phy I").build();

            message = null;

            article = Article.builder()
                    .articleId((Integer) articleDTO.getArticleId())
                    .authorEmailAddress((String) articleDTO.getAuthorEmailAddress())
                    .isActive((Boolean)articleDTO.getIsActive())
                    .authorName((String) articleDTO.getAuthorName())
                    .isPublished((Boolean) articleDTO.getIsPublished())
                    .noOfPages((Integer) articleDTO.getNoOfPages())
                    .shortTitle((String)articleDTO.getShortTitle())
                    .title((String)articleDTO.getTitle()).build();




    }

    @AfterEach
    void tearDown() {
        articleDTO = null;
        article = null;
        articleDTOInvalid = null;
    }
    @Test
    void publishArticle_returns_response_status_as_OK() throws Exception{

        doNothing().when(schemaValidator).validateArticleAgainstSchema(any());
        doNothing().when(jmsTemplate).convertAndSend(message,article);
        publishService.publishArticle(articleDTO);
        verify(schemaValidator,times(1)).validateArticleAgainstSchema(any());

    }

    @Test
    void publishArticleThrowsValidationExcepion() throws Exception{
        doThrow(ValidationException.class).when(schemaValidator).validateArticleAgainstSchema(any());
        assertThrows(ValidationException.class,()->publishService.publishArticle(articleDTO));
    }
}