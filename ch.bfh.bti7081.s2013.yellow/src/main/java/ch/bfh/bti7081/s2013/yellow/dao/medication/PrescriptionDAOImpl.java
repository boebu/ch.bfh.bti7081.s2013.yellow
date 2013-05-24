package ch.bfh.bti7081.s2013.yellow.dao.medication;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import org.springframework.stereotype.Repository;

/**
 * @author Andy Pollari
 * Implementation of PrescriptionDAOImpl
 */
@Repository
public class PrescriptionDAOImpl extends GenericDAOImpl<Prescription> implements PrescriptionDAO {

	public PrescriptionDAOImpl() {
		super(Prescription.class);
	}
}
