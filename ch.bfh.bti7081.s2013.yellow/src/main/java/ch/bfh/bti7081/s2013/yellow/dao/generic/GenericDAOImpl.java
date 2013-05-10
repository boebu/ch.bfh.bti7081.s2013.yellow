package ch.bfh.bti7081.s2013.yellow.dao.generic;

import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import ch.bfh.bti7081.s2013.yellow.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

    /**
     * JPA implementation of the GenericRepository. Note that this
     * implementation also expects Hibernate as JPA implementation. That's
     * because we like the Criteria API.
     *
     * @author Jurgen Lust
     * @author $LastChangedBy: jlust $
     * @version $LastChangedRevision: 257 $
     * @param <T>
     * The persistent type
     * @param <ID>
     * The primary key type
     */

    // ~ Instance fields
    // --------------------------------------------------------

    private final Class<T> persistentClass;
    private EntityManager entityManager;
    
    @Autowired
    private UserService userService;

    // ~ Constructor
    // -----------------------------------------------------------

    public GenericDAOImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    // ~ Methods
    // ----------------------------------------------------------------

    @Override
    public long countAll() {
        return countByCriteria();
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public T findById(int id) {
        final T result = getEntityManager().find(persistentClass, id);
        return result;
    }

    @Override
    public Class<T> getEntityClass() {
        return persistentClass;
    }

    @Override
    public T getSafeSingleResult(TypedQuery<T> q) {
        try{
            return q.getSingleResult();
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * set the JPA entity manager to use.
     *
     * @param entityManager
     */
    // @Required
    @Override
    @PersistenceContext(unitName = "yellow-pu")
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    public List<T> findByCriteria(final Criterion... criterion) {
        return findByCriteria(-1, -1, criterion);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();
        return result;
    }

    @Override
    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    @Override
    public long countByCriteria(Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return (Long) crit.list().get(0);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }
    
    @Override
    public void detach(T entity){
        getEntityManager().detach(entity);
    }

    @Override
    public T save(T entity) {

        if (entity == null)
            return null;
        if (getEntityManager().contains(entity))
            return entity;
        if (entity instanceof YellowEntity) {
            YellowEntity a = (YellowEntity) entity;
            Date now = new Date();
            if (a.getCreation() == null)
                a.setCreation(now);

            a.setLastUpdated(now);
            a.setVersion(a.getVersion() + 1);
            if (userService.getLoggedInUser() != null)
                a.setUpdatedBy(userService.getLoggedInUser());
            T prev = findById(a.getId());
            if (prev == null) {
                getEntityManager().persist(a);
                return entity;
            } else {
                return getEntityManager().merge(entity);
            }

        }
        return null;
    }

}
