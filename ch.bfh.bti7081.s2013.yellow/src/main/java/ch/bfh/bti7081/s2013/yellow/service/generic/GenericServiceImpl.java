package ch.bfh.bti7081.s2013.yellow.service.generic;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAO;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GenericServiceImpl<T> implements GenericService<T> {

    private GenericDAO dao;

    public GenericServiceImpl() {
    }

    // ~ Methods
    // ----------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public long countAll() {
        return dao.countByCriteria();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return dao.findByCriteria();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(int id) {
        return (T) dao.findById(id);
    }


    @Override
    public List<T> findByCriteria(final Criterion... criterion) {
        return dao.findByCriteria(criterion);
    }

    @Override
    public List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) {
        return dao.findByCriteria(firstResult,maxResults,criterion);
    }

    @Override
    public long countByCriteria(Criterion... criterion) {
        return dao.countByCriteria(criterion);
    }


    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        return dao.getEntityClass();
    }


    @Override
    @SuppressWarnings("unchecked")
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        return (T) dao.save(entity);
    }

    @Override
    public void setDAO(GenericDAO dao) {
        this.dao = dao;
    }

    @Override
    public GenericDAO getDAO() {
        return dao;
    }

    @Override
    public void refresh(T entity) {
        dao.refresh(entity);
    }

    @Override
    public void detach(T entity){
        dao.detach(entity);
    }

}
