package cz.falcon9.redact.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import cz.falcon9.redact.backend.data.models.articles.ArticleReview;

public interface ArticleReviewRepository extends CrudRepository<ArticleReview, String> {

}
