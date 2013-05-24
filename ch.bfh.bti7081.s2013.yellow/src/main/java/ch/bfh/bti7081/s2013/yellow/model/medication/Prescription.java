package ch.bfh.bti7081.s2013.yellow.model.medication;

import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Andy Pollari
 * This Class represents a presciption.
 */
@Entity
public class Prescription extends YellowEntity<Prescription>{
    public Prescription() {
        super(Prescription.class);
    }

	@Min(1)
	@Max(15)
	private int quantity;

	@NotNull
	private Date validFrom;

	private Date validUntil;

	@Min(1)
	private int intervallInHours;

	private String comment;

	private Date lastTaken;

	private boolean deactivated;

	@ManyToOne(optional = false)
	@NotNull
	private Medicament medicament;

	@ManyToOne(optional = false)
	@NotNull
	private Patient patient;


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public int getIntervallInHours() {
		return intervallInHours;
	}

	public void setIntervallInHours(int intervallInHours) {
		this.intervallInHours = intervallInHours;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastTaken() {
		return lastTaken;
	}

	public void setLastTaken(Date lastTaken) {
		this.lastTaken = lastTaken;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public boolean isDeactivated() {
		return deactivated;
	}

	public void setDeactivated(boolean deactivated) {
		this.deactivated = deactivated;
	}
}
