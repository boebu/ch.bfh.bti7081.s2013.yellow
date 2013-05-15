package ch.bfh.bti7081.s2013.yellow.dao.person;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import ch.bfh.bti7081.s2013.yellow.model.person.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class PatientDAOImpl extends GenericDAOImpl<Patient> implements PatientDAO {

	public PatientDAOImpl() {
		super(Patient.class);
	}
}
