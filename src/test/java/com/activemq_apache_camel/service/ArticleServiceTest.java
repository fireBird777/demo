//package com.activemq_apache_camel.service;
//
//
//import com.activemq_apache_camel.model.Article;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class ArticleServiceTest {
//
//
//    //ArticleDaoI articleDao;
//
//    @Mock
//    ArticleService articleService;
//
//    private Article article1 ;
//    private Article article2;
//
//    @BeforeEach
//    public void init()
//    {
//        article1 = Article.builder().articleId(1)
//        .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
//        .title("cnn").shortTitle("jcj").build();
//
//        article2 = Article.builder().articleId(2)
//                .authorEmailAddress("mno").authorName("jkl").isActive(true).isPublished(false).noOfPages(101)
//                .title("abc").shortTitle("def").build();
//    }
//
//    @Test
//    void findAll() {
//
//        List<Article> articles = new ArrayList<>();
//        articles.add(article1);
//        articles.add(article2);
//
//        doReturn(articles).when(articleService).findAll();
//
//        List<Article> returnedList = articleService.findAll();
//
//        assertEquals(2,returnedList.size());
//
//        verify(articleService,times(1)).findAll();
//
//
//    }
//
//    @Test
//    void findById() {
//
//        doReturn(Optional.of(article1)).when(articleService).findById(1);
//
//        Optional<Article> returnedArticle = articleService.findById(1);
//
//// parametarize test cases
//        assertEquals(1,returnedArticle.get().getArticleId(),"article not present");
//
//        verify(articleService,times(1)).findById(1);
//
//
//    }
//
//    @Test
//    void save() {
//
//        doReturn(new ResponseEntity<String>( "saved",HttpStatus.OK)).when(articleService).save(article1);
//        ResponseEntity<String> returnedArticle = articleService.save(article1);
//        assertEquals(HttpStatus.OK, returnedArticle.getStatusCode());
//        verify(articleService,times(1)).save(article1);
//    }
//
//    @Test
//    void deleteById() {
//        articleService.deleteById(1);
//        verify(articleService,times(1)).deleteById(1);
//    }
//}