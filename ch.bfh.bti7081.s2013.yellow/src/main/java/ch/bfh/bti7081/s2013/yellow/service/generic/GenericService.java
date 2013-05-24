package ch.bfh.bti7081.s2013.yellow.service.generic;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAO;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface GenericService<T> {
    /**
     *
     * @return number of all records
     */
    long countAll();

    /**
     *
     * @return all records
     */
    List<T> findAll();

    /**
     *
     * @param id
     * @return element with given id
     */
    T findById(int id);

    /**
     *
     * @return class of entity
     */
    Class<T> getEntityClass();

    /**
     *
     * @param entity the object you want to delete
     */
    void delete(T entity);

    /**
     *
     * @param entity
     * @return flushed entity
     */
    T save(T entity);

    /**
     *
     * @param dao
     */
    void setDAO(GenericDAO dao);

    /**
     *
     * @return dao
     */
    GenericDAO getDAO();
    /**
     * refreshs the entity
     * @param entity
     */
    void refresh(T entity);

    /**
     *
     * @param firstResult
     * @param maxResults
     * @param criterion
     * @return matching records
     */
    List<T> findByCriteria(int firstResult, int maxResults, Criterion... criterion);

    /**
     *
     * @param criterion multiple criterions
     * @return matching records
     */
    List<T> findByCriteria(Criterion... criterion);

    /**
     *
     * @param criterion
     * @return matching records
     */
    long countByCriteria(Criterion... criterion);

    /**
     * detaches the entity
     * @param entity
     */
    void detach(T entity);
}
