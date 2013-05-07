package at.dinauer.fhbay.interfaces.dao;


import java.util.Set;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Article;



@Local
public interface ArticleDao extends Dao<Article, Long> {
	Set<Article> findByCategoryAndPattern(Long categoryId, String pattern);
}
