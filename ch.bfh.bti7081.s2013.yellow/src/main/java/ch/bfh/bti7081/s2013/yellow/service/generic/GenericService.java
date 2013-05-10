package ch.bfh.bti7081.s2013.yellow.service.generic;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAO;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface GenericService<T> {
    long countAll();


    List<T> findAll();


    T findById(int id);

    Class<T> getEntityClass();

    void delete(T entity);

    T save(T entity);
    
    void setDAO(GenericDAO dao);

    GenericDAO getDAO();
    
    void refresh(T entity);

    List<T> findByCriteria(int firstResult, int maxResults, Criterion... criterion);

    List<T> findByCriteria(Criterion... criterion);

    long countByCriteria(Criterion... criterion);

    void detach(T entity);
}
