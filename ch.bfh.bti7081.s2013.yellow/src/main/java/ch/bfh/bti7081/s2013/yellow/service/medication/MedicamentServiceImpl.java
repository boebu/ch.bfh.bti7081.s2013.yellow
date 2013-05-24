package ch.bfh.bti7081.s2013.yellow.service.medication;

import ch.bfh.bti7081.s2013.yellow.dao.medication.MedicamentDAO;
import ch.bfh.bti7081.s2013.yellow.model.medication.Medicament;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author Andy Pollari
 * Implementation of MedicamentService
 */
@Transactional
@Service("medicamentService")
public class MedicamentServiceImpl extends GenericServiceImpl<Medicament> implements MedicamentService {

    @Autowired
    MedicamentDAO medicamentDAO;

	@PostConstruct
	public void init(){
		setDAO(medicamentDAO);
	}

}
