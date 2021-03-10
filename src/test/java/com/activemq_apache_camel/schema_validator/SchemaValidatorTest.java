package com.activemq_apache_camel.schema_validator;

import com.activemq_apache_camel.model.ArticleDTO;
import org.everit.json.schema.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {

    private ArticleDTO articleDTO;

    @BeforeEach
    void setUp() {
        articleDTO = ArticleDTO.builder().articleId(1)
                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
                .title("cnn").shortTitle("jcj").build();
    }

    @AfterEach
    void tearDown() {
        articleDTO =null;
    }

    SchemaValidator schemaValidator = new SchemaValidator();

    @Test
    void validateArticleAgainstSchema_throws_no_exception() {

        assertDoesNotThrow(()->schemaValidator.validateArticleAgainstSchema(articleDTO));
    }

}