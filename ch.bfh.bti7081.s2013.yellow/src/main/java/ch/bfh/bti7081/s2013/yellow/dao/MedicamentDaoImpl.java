package ch.bfh.bti7081.s2013.yellow.dao;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.medication.Medicament;
import org.springframework.stereotype.Repository;

/**
 * Implementation of MedicamentDao
 */
@Repository
public class MedicamentDaoImpl extends GenericDAOImpl<Medicament> implements MedicamentDao {
    public MedicamentDaoImpl() {
        super(Medicament.class);
    }
}
