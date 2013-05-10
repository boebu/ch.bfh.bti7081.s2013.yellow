package ch.bfh.bti7081.s2013.yellow.dao.generic;

import org.hibernate.criterion.Criterion;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface GenericDAO<T> {
    long countAll();

    List<T> findAll();

    T findById(int id);

    Class<T> getEntityClass();

    // @Required
    void setEntityManager(EntityManager entityManager);

    EntityManager getEntityManager();

    void delete(T entity);

    T save(T entity);

    public long countByCriteria(Criterion... criterion);

    public List<T> findByCriteria(final Criterion... criterion);

    public List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion);

    public void refresh(T entity);

    void detach(T entity);

    T getSafeSingleResult(TypedQuery<T> q);
}
