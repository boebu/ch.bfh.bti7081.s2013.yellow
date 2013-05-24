package ch.bfh.bti7081.s2013.yellow.service.person;

import ch.bfh.bti7081.s2013.yellow.dao.person.PatientDAO;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
/**
 * @author Andy Pollari
 * Implementation of PatientService
 */
@Transactional
@Service("patientService")
public class PatientServiceImpl extends GenericServiceImpl<Patient> implements PatientService {

    @Autowired
    PatientDAO patientDAO;

	@PostConstruct
	public void init(){
		setDAO(patientDAO);
	}

}
