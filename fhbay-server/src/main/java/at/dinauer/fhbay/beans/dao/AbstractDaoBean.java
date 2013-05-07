package at.dinauer.fhbay.beans.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDaoBean<T, ID extends Serializable> {

	@PersistenceContext
	private EntityManager entityManager;
	

	@SuppressWarnings("unchecked")
	public AbstractDaoBean() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		entityType = (Class<T>) thisType.getActualTypeArguments()[0];
	}

	public void persist(T entity) {
		getEntityManager().persist(entity);
	}

	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	private Class<T> entityType;
	public T findById(ID id) {
		// T.class nicht moeglich, deshalb Class<T>
		return getEntityManager().find(entityType, id);
	}

	public Collection<T> findAll() {
		return getEntityManager().createQuery(String.format("SELECT entity FROM %s entity", entityType.getName()), entityType).getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}