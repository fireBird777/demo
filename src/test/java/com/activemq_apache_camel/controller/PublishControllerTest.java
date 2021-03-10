package com.activemq_apache_camel.controller;

import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.model.ArticleDTO;
import com.activemq_apache_camel.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PublishControllerTest {

    @MockBean
    PublishService publishService;

    @InjectMocks
    PublishController publishController = new PublishController();

    private ArticleDTO articleDTO;

    @Autowired
    MockMvc mockMvc;

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

    @Test
    void publishArticle_returns_response_OK() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String response = null;
        doNothing().when(publishService).publishArticle(articleDTO);
        MvcResult result = mockMvc.perform(post("/publisher/articles")
                .content(om.writeValueAsString(articleDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        verify(publishService,times(1)).publishArticle(any(ArticleDTO.class));
    }

    @Test
    void publishArticle_returns_response_BAD_REQUEST() throws Exception {

        ObjectMapper om = new ObjectMapper();
        String response = null;
        doThrow(ValidationException.class).when(publishService).publishArticle(any());
        MvcResult result = mockMvc.perform(post("/publisher/articles")
                .content(om.writeValueAsString(articleDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        verify(publishService,times(1)).publishArticle(any(ArticleDTO.class));
    }
}