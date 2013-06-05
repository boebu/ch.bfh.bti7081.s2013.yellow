package ch.bfh.bti7081.s2013.yellow.model.person;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Andy Pollari
 * represents a patient
 */
@Entity
@Table(name="Patient")
public class Patient extends Person<Patient> {
    public Patient() {
        super(Patient.class);
    }
	@OneToMany(mappedBy = "patient")
	private List<Prescription> prescriptions;


	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	

	
}
