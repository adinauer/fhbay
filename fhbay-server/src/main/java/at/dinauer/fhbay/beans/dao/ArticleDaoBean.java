package at.dinauer.fhbay.beans.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;


@Stateless
public class ArticleDaoBean extends AbstractDaoBean<Article, Long> implements ArticleDao {

	public List<Article> findByCategoryAndPattern(Long categoryId, String pattern) {
		TypedQuery<Article> articleQuery = getEntityManager().createQuery("SELECT a FROM Article a INNER JOIN a.categories c WHERE c.id = :catId AND (lower(a.name) LIKE :pattern OR lower(a.description) LIKE :pattern)", Article.class);
		
		articleQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%");
		articleQuery.setParameter("catId", categoryId);
		
		return articleQuery.getResultList();
	}

	public List<Article> findByCategoryAndPattern(String pattern) {
		TypedQuery<Article> articlesByPatternQuery = getEntityManager().createQuery("SELECT a FROM Article a WHERE (lower(a.name) LIKE :pattern OR lower(a.description) LIKE :pattern)", Article.class);
		
		articlesByPatternQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%");
		
		return articlesByPatternQuery.getResultList();
	}
}
