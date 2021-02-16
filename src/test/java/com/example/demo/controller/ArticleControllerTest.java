package com.example.demo.controller;

import com.example.demo.dao.ArticleDaoI;
import com.example.demo.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.integration.test.util.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @MockBean
    ArticleDaoI articleDaoI;

    @Autowired
    MockMvc mockMvc;


    @Test
    void save() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Article article = new Article();
        article.setArticleId(1);
        article.setActive(true);
        article.setPublished(false);
        article.setNoOfPages(10);
        article.setAuthorName("njns");
        article.setTitle("cnn");
        article.setShortTitle("jcj");
        article.setAuthorEmailAddress("cjn");

        when(articleDaoI.save(any(Article.class))).thenReturn(article);

        MvcResult result = mockMvc.perform(post("/SaveArticle")
                 .content(om.writeValueAsString(article))
                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
        assertEquals("saved",response);


        verify(articleDaoI,times(1)).save(any(Article.class));



    }

    @Test
    void findAll() throws Exception {
        Article article1 = new Article();
        article1.setArticleId(1);
        article1.setActive(true);
        article1.setPublished(false);
        article1.setNoOfPages(10);
        article1.setAuthorName("njns");
        article1.setTitle("cnn");
        article1.setShortTitle("jcj");
        article1.setAuthorEmailAddress("cjn");

        Article article2 = new Article();
        article2.setArticleId(2);
        article2.setActive(true);
        article2.setPublished(false);
        article2.setNoOfPages(101);
        article2.setAuthorName("jkl");
        article2.setTitle("abc");
        article2.setShortTitle("def");
        article2.setAuthorEmailAddress("mno");


        List<Article> articles = Arrays.asList(article1,article2);
        when(articleDaoI.findAll()).thenReturn(articles);
        mockMvc.perform(MockMvcRequestBuilders.get("/Articles"))
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
        verify(articleDaoI, times(1)).findAll();

    }

    @Test
    void findById() throws Exception {
        Article article = new Article();
        article.setArticleId(1);
        article.setActive(true);
        article.setPublished(false);
        article.setNoOfPages(10);
        article.setAuthorName("njns");
        article.setTitle("cnn");
        article.setShortTitle("jcj");
        article.setAuthorEmailAddress("cjn");
        when(articleDaoI.findById(1)).thenReturn(Optional.of(article));
        mockMvc.perform(MockMvcRequestBuilders.get("/Articles/1").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articleId", is(1)))
                .andExpect(jsonPath("$.title", is("cnn")))
                .andExpect(jsonPath("$.shortTitle", is("jcj")))
                .andExpect(jsonPath("$.noOfPages", is(10)))
                .andExpect(jsonPath("$.authorName", is("njns")))
                .andExpect(jsonPath("$.authorEmailAddress", is("cjn")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.published", is(false)));
        verify(articleDaoI,times(1)).findById(1);


    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(articleDaoI).deleteById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/Articles/1")).andExpect(status().isOk());
        verify(articleDaoI,times(1)).deleteById(1);
    }


}