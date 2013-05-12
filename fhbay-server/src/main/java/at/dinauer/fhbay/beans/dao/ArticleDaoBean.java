package at.dinauer.fhbay.beans.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;


@Stateless
public class ArticleDaoBean extends AbstractDaoBean<Article, Long> implements ArticleDao {

	public List<Article> findByCategoryAndPattern(Long categoryId, String pattern) {
		TypedQuery<Article> articleQuery = getEntityManager().createNamedQuery("qryFindByCategoryAndPattern", Article.class);
		
		articleQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%");
		articleQuery.setParameter("catId", categoryId);
		
		return articleQuery.getResultList();
	}
}
