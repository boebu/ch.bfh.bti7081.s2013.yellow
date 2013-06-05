package ch.bfh.bti7081.s2013.yellow.service.medication;

import ch.bfh.bti7081.s2013.yellow.dao.medication.PrescriptionDAO;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


import java.util.Date;
import java.util.List;

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

	@Override
	public List<Prescription> findActive() {
		return prescriptionDAO.findByCriteria(Restrictions.eq("deactivated", false));
	}

	@Override
	public List<Prescription> findActiveandInRange() {
		Date a = new Date();
		Conjunction c = new Conjunction();
		c.add(Restrictions.lt("validFrom", a));
		c.add(Restrictions.ge("validUntil",a));
		c.add(Restrictions.eq("deactivated", false));
		return prescriptionDAO.findByCriteria(c);
	}

}
