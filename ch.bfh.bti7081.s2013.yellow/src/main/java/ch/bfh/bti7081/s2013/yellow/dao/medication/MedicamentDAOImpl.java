package ch.bfh.bti7081.s2013.yellow.dao.medication;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.medication.Medicament;
import org.springframework.stereotype.Repository;

/**
 * @author Andy Pollari
 * Implementation of MedicamentDAO
 */
@Repository
public class MedicamentDAOImpl extends GenericDAOImpl<Medicament> implements MedicamentDAO {
    public MedicamentDAOImpl() {
        super(Medicament.class);
    }
}
