package at.dinauer.fhbay.interfaces;

import java.util.List;

import javax.ejb.Remote;

import at.dinauer.fhbay.domain.Article;

@Remote
public interface ArticleAdminRemote extends ArticleAdmin {}
