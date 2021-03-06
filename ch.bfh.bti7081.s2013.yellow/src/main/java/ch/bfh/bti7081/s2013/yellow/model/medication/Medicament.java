package ch.bfh.bti7081.s2013.yellow.model.medication;

import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Andy Pollari
 * This Class represents a medicament.
 */
@Entity
public class Medicament extends YellowEntity<Medicament>{
    public Medicament() {
        super(Medicament.class);
    }
    @Basic(optional = false)
    @Column(length = 60)
    String name;

    @Basic(optional = true)
    @Column(length = 60)
    String category;

    @Basic(optional = true)
    Byte[] img;

	@OneToMany(mappedBy = "medicament")
	private List<Prescription> prescriptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }


	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
}
