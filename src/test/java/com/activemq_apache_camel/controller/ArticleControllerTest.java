package com.activemq_apache_camel.controller;

import com.activemq_apache_camel.model.Article;
import com.activemq_apache_camel.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @MockBean
    ArticleService articleService;

    @InjectMocks
    ArticleController articleController = new ArticleController();

    @Autowired
    MockMvc mockMvc;

    private Article article1;
    private Article article2;

    @BeforeEach
    public void setUp()
    {
                article1 = Article.builder().articleId(1)
                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
                .title("cnn").shortTitle("jcj").build();

                 article2 = Article.builder().articleId(2)
                .authorEmailAddress("mno").authorName("jkl").isActive(true).isPublished(false).noOfPages(101)
                .title("abc").shortTitle("def").build();
    }

    @AfterEach
    public void tearDown()
    {
        article1 = null;
        article2 = null;
    }

    @Test
    void save_returns_response_status_as_OK() throws Exception {
        ObjectMapper om = new ObjectMapper();
        when(articleService.save(any(Article.class))).thenReturn(new ResponseEntity<>("saved", HttpStatus.OK));

        MvcResult result = mockMvc.perform(post("/articles")
                 .content(om.writeValueAsString(article1))
                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
        assertEquals("saved",response);


        verify(articleService,times(1)).save(any(Article.class));



    }


    @Test
    void save_returns_response_as_INTERNAL_SERVER_ERROR() throws Exception {
        ObjectMapper om = new ObjectMapper();
        doReturn(new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR)).when(articleService).save(any(Article.class));
        MvcResult result = mockMvc.perform(post("/articles")
                .content(om.writeValueAsString(article1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError()).andReturn();
        //String response = result.getResponse().getContentAsString();
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),result.getResponse().getStatus());


        verify(articleService,times(1)).save(any(Article.class));



    }



    @Test
    void findAll_retuns_list_of_articles() throws Exception {

        List<Article> articles = Arrays.asList(article1,article2);
        when(articleService.findAll()).thenReturn(articles);
        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].articleId", is(1)))
                .andExpect(jsonPath("$[0].title", is("cnn")))
                .andExpect(jsonPath("$[0].shortTitle", is("jcj")))
                .andExpect(jsonPath("$[0].noOfPages", is(10)))
                .andExpect(jsonPath("$[0].authorName", is("njns")))
                .andExpect(jsonPath("$[0].authorEmailAddress", is("cjn")))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].published", is(false)))
                .andExpect(jsonPath("$[1].articleId", is(2)))
                .andExpect(jsonPath("$[1].title", is("abc")))
                .andExpect(jsonPath("$[1].shortTitle", is("def")))
                .andExpect(jsonPath("$[1].noOfPages", is(101)))
                .andExpect(jsonPath("$[1].authorName", is("jkl")))
                .andExpect(jsonPath("$[1].authorEmailAddress", is("mno")))
                .andExpect(jsonPath("$[1].active", is(true)))
                .andExpect(jsonPath("$[1].published", is(false)));
        verify(articleService, times(1)).findAll();

    }

    @Test
    void findById_returns_article() throws Exception {
        when(articleService.findById(1)).thenReturn(article1);
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articleId", is(1)))
                .andExpect(jsonPath("$.title", is("cnn")))
                .andExpect(jsonPath("$.shortTitle", is("jcj")))
                .andExpect(jsonPath("$.noOfPages", is(10)))
                .andExpect(jsonPath("$.authorName", is("njns")))
                .andExpect(jsonPath("$.authorEmailAddress", is("cjn")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.published", is(false)));
        verify(articleService,times(1)).findById(1);


    }

    @Test
    void deleteById_returns_response_OK() throws Exception {
        doReturn(new ResponseEntity<>("deleted", HttpStatus.OK)).when(articleService).deleteById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1")).andExpect(status().isOk());
        verify(articleService,times(1)).deleteById(1);
    }
    @Test
    void deleteById_returns_response_INTERNAL_SERVER_ERROR() throws Exception {
        doReturn(new ResponseEntity<>("error", HttpStatus.NOT_FOUND)).when(articleService).deleteById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1")).andExpect(status().isNotFound());
        verify(articleService,times(1)).deleteById(1);
    }

    // write test for delete by id retuns BAD REQuest status code


}