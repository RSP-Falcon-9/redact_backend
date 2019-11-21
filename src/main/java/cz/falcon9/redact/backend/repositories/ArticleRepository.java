package cz.falcon9.redact.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleRepository extends CrudRepository<Article, String> {
    
    List<Article> findAll();
    
    @Query("SELECT a FROM Article a WHERE a.user.userName = :authorId")
    List<Article> findAllByAuthor(@Param("authorId") String authorId);
    
}

