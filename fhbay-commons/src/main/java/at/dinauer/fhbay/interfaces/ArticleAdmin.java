package at.dinauer.fhbay.interfaces;

import java.util.List;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.exceptions.IdNotFoundException;

public interface ArticleAdmin {
	Article findArticleById(Long id) throws IdNotFoundException;

	List<Article> findAllMatchingArticles(Long categoryId, String pattern,
		boolean includeSubCategories) throws IdNotFoundException;

	Long offerArticle(Article article, Long sellerId)
		throws IdNotFoundException;

//	Category findCategoryById(Long id) throws IdNotFoundException;
//
//	List<Category> findAllRootCategories();
//
//	Category findCategoryTree(Long categoryId) throws IdNotFoundException;
//
//	Long saveCategory(Category category);

//	void assignArticleToCategory(Long articleId, Long categoryId)
//		throws IdNotFoundException;
}