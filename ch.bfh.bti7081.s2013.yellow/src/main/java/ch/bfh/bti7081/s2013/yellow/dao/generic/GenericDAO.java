package ch.bfh.bti7081.s2013.yellow.dao.generic;

import org.hibernate.criterion.Criterion;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Jurgen Lust
 * @author $LastChangedBy: jlust $
 * generic dao interface, providing basic CRUD operations
 * It provides all generic data acces methods such as findAll(), findById(id), delete(entity),...
 * @param <T> the entity type
 */
public interface GenericDAO<T> {

    /**
     * counts all entries in the table
     * @return number of entries in the table
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
     * @return class of the entity
     */
    Class<T> getEntityClass();

    /**
     * set the entityManager
     * @param entityManager
     */
    void setEntityManager(EntityManager entityManager);

    /**
     *
     * @return the EntityManager
     */
    EntityManager getEntityManager();

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
     * @param criterion
     * @return number of records which matches with the given criterias
     */
    public long countByCriteria(Criterion... criterion);

    /**
     *
     * @param criterion
     * @return list of objects which matches with the given criterias
     */
    public List<T> findByCriteria(final Criterion... criterion);

    /**
     *
     * @param firstResult
     * @param maxResults
     * @param criterion
     * @return list of objects which matches with the given criterias
     */
    public List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion);

    /**
     * refreshs the entity
     * @param entity
     */
    public void refresh(T entity);

    /**
     * detaches the entity
     * @param entity
     */
    void detach(T entity);

    /**
     * detaches the entity
     * @param q
     * @return
     */
    T getSafeSingleResult(TypedQuery<T> q);
}
