package ch.bfh.bti7081.s2013.yellow.service.medication;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;

import java.util.List;


/**
 * @author Andy Pollari
 * Interface for Prescription Service
 */
public interface PrescriptionService extends GenericService<Prescription> {

	/**
	 * Finds the active prescriptions (disabled == true)
	 * @return The active prescriptions.
	 */
	List<Prescription> findActive();
}
