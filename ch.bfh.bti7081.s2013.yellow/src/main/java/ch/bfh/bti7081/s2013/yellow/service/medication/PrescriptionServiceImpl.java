package ch.bfh.bti7081.s2013.yellow.service.medication;

import ch.bfh.bti7081.s2013.yellow.dao.medication.PrescriptionDAO;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author Andy Pollari
 * Implementation for Prescription Service
 */
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
