package at.dinauer.fhbay.beans.dao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;


@Stateless
public class ArticleDaoBean extends AbstractDaoBean<Article, Long> implements ArticleDao {

	public List<Article> findByCategoryAndPattern(Long categoryId, String pattern) {
		// non named query
//		TypedQuery<Article> articleQuery = getEntityManager().createQuery(
////			"SELECT a FROM Article a, INNER JOIN a.categories c WHERE c.id = :catId AND (lower(a.name) LIKE :pattern OR lower(a.description) LIKE :pattern)", 
//			"SELECT a FROM Article a WHERE lower(a.name) LIKE :pattern OR lower(a.description) LIKE :pattern",
//			Article.class);
		
		// named query
		TypedQuery<Article> articleQuery = getEntityManager().createNamedQuery("qryFindByCategoryAndPattern", Article.class);
		
		articleQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%");
		
		// TODO: improve (replace set with list)
		return articleQuery.getResultList();
	}
}
