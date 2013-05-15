package ch.bfh.bti7081.s2013.yellow.service.medication;

import ch.bfh.bti7081.s2013.yellow.dao.medication.PrescriptionDAO;
import ch.bfh.bti7081.s2013.yellow.dao.person.PatientDAO;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import ch.bfh.bti7081.s2013.yellow.service.person.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("prescriptionService")
public class PrescriptionServiceImpl extends GenericServiceImpl<Prescription> implements PrescriptionService {

    @Autowired
    PrescriptionDAO prescriptionDAO;

	@PostConstruct
	public void init(){
		setDAO(prescriptionDAO);
	}

}
