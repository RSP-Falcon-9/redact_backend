package cz.falcon9.redact.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.repositories.ArticleRepository;

@Service
@Secured("ROLE_AUTHOR")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepo;
    
    @Override
    public List<Article> getAllArticles(String authorId) {
        return articleRepo.findAllByAuthor(authorId);
    }

}
