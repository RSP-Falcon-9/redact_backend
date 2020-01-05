package cz.falcon9.redact.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleRepository extends CrudRepository<Article, String> {
    
    // TODO: Pagination
    List<Article> findAll();
    
    @Query("SELECT a FROM Article a WHERE a.user.userName = :authorId")
    List<Article> findAllByAuthor(@Param("authorId") String authorId);
    
    @Query("SELECT a FROM Article a JOIN a.versions v WHERE v.status = cz.falcon9.redact.backend.data.articles.ArticleStatus.NEW")
    List<Article> findNew();
    
    @Query("SELECT DISTINCT a FROM Article a JOIN a.versions v JOIN v.reviews r WHERE r.reviewer.userName = :reviewerId")
    List<Article> findByReviewer(@Param("reviewerId") String reviewerId);
    
    @Query("SELECT a FROM Article a WHERE a.edition.id = :edition")
    List<Article> findByEdition(@Param("edition") int edition);
    
}

