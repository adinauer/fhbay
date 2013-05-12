package at.dinauer.fhbay.beans.dao;


import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.interfaces.dao.CategoryDao;


@Stateless
public class CategoryDaoBean extends AbstractDaoBean<Category, Long> implements CategoryDao {}
