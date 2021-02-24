//package com.activemq_apache_camel.controller;
//
//import com.activemq_apache_camel.repository.ArticleRepository;
//import com.activemq_apache_camel.model.Article;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ArticleControllerTest {
//
//    @MockBean
//    ArticleRepository articleRepository;
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @Test
//    void save() throws Exception {
//        ObjectMapper om = new ObjectMapper();
//        Article article = Article.builder().articleId(1)
//                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
//                .title("cnn").shortTitle("jcj").build();
//
//        when(articleRepository.save(any(Article.class))).thenReturn(article);
//
//        MvcResult result = mockMvc.perform(post("/articles")
//                 .content(om.writeValueAsString(article))
//                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk()).andReturn();
//        String response = result.getResponse().getContentAsString();
//        assertNotNull(response);
//        assertEquals("saved",response);
//
//
//        verify(articleRepository,times(1)).save(any(Article.class));
//
//
//
//    }
//
//    @Test
//    void findAll() throws Exception {
//        Article article1 = Article.builder().articleId(1)
//                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
//                .title("cnn").shortTitle("jcj").build();
//
//        Article article2 = Article.builder().articleId(2)
//                .authorEmailAddress("mno").authorName("jkl").isActive(true).isPublished(false).noOfPages(101)
//                .title("abc").shortTitle("def").build();
//
//
//        List<Article> articles = Arrays.asList(article1,article2);
//        when(articleRepository.findAll()).thenReturn(articles);
//        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].articleId", is(1)))
//                .andExpect(jsonPath("$[0].title", is("cnn")))
//                .andExpect(jsonPath("$[0].shortTitle", is("jcj")))
//                .andExpect(jsonPath("$[0].noOfPages", is(10)))
//                .andExpect(jsonPath("$[0].authorName", is("njns")))
//                .andExpect(jsonPath("$[0].authorEmailAddress", is("cjn")))
//                .andExpect(jsonPath("$[0].active", is(true)))
//                .andExpect(jsonPath("$[0].published", is(false)))
//                .andExpect(jsonPath("$[1].articleId", is(2)))
//                .andExpect(jsonPath("$[1].title", is("abc")))
//                .andExpect(jsonPath("$[1].shortTitle", is("def")))
//                .andExpect(jsonPath("$[1].noOfPages", is(101)))
//                .andExpect(jsonPath("$[1].authorName", is("jkl")))
//                .andExpect(jsonPath("$[1].authorEmailAddress", is("mno")))
//                .andExpect(jsonPath("$[1].active", is(true)))
//                .andExpect(jsonPath("$[1].published", is(false)));
//        verify(articleRepository, times(1)).findAll();
//
//    }
//
//    @Test
//    void findById() throws Exception {
//        Article article = Article.builder().articleId(1)
//                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
//                .title("cnn").shortTitle("jcj").build();
//
//        when(articleRepository.findById(1)).thenReturn(Optional.of(article));
//        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.articleId", is(1)))
//                .andExpect(jsonPath("$.title", is("cnn")))
//                .andExpect(jsonPath("$.shortTitle", is("jcj")))
//                .andExpect(jsonPath("$.noOfPages", is(10)))
//                .andExpect(jsonPath("$.authorName", is("njns")))
//                .andExpect(jsonPath("$.authorEmailAddress", is("cjn")))
//                .andExpect(jsonPath("$.active", is(true)))
//                .andExpect(jsonPath("$.published", is(false)));
//        verify(articleRepository,times(1)).findById(1);
//
//
//    }
//
//    @Test
//    void deleteById() throws Exception {
//        doNothing().when(articleRepository).deleteById(1);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1")).andExpect(status().isOk());
//        verify(articleRepository,times(1)).deleteById(1);
//    }
//
//
//}