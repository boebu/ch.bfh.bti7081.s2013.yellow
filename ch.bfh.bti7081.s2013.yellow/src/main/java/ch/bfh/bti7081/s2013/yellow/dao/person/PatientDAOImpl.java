package ch.bfh.bti7081.s2013.yellow.dao.person;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import org.springframework.stereotype.Repository;

/**
 * @author Andy Pollari
 * Implementation of PatientDAOImpl
 */
@Repository
public class PatientDAOImpl extends GenericDAOImpl<Patient> implements PatientDAO {

	public PatientDAOImpl() {
		super(Patient.class);
	}
}
