package ch.bfh.bti7081.s2013.yellow.ui.medication;

import ch.bfh.bti7081.s2013.yellow.model.medication.Medicament;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import ch.bfh.bti7081.s2013.yellow.service.medication.MedicamentService;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.person.PatientService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;
import com.vaadin.annotations.Title;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;


/**
 * @author Janosch Rohdewald
 * UI component for creating and editing prescriptions
 */
@Title("Prescription")
public class PrescriptionUI extends FormLayout{
	PrescriptionService prescriptionService;
	MedicamentService medicamentService;
	PatientService patientService;

	private FieldGroup fields = new FieldGroup();
	private BeanItem<Prescription> item;
	Prescription prescription;

	public PrescriptionUI() {
		this(-1);
	}
	public PrescriptionUI(int prescriptionId) {
		SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		prescriptionService = (PrescriptionService) springHelper.getBean("prescriptionService");
		medicamentService = (MedicamentService) springHelper.getBean("medicamentService");
		patientService = (PatientService) springHelper.getBean("patientService");

		prescription = null;

		// Search for the prescription
		try {
			prescription = prescriptionService.findById(prescriptionId);
		} catch (NumberFormatException e) {
		}
		if (prescription == null)
			prescription = new Prescription();

		// Set the prescription as the data source
		item = new BeanItem(prescription);
		fields.setItemDataSource(item);

		// Dropdown for the patient selection
		ComboBox patientCombo = new ComboBox("Patient");
		BeanItemContainer<Patient> patients = new BeanItemContainer<>(Patient.class);
		patients.addAll(patientService.findAll());
		patientCombo.setContainerDataSource(patients);
		patientCombo.addValidator(new BeanValidator(Prescription.class, "patient"));
		// Display patient with name and firstname
		for (Patient patient : patientService.findAll())
			patientCombo.setItemCaption(patient, patient.getName() + " " + patient.getVorname());
		fields.bind(patientCombo, "patient");
		addComponent(patientCombo);

		// Dropdown for the medicament selection
		ComboBox medicamentCombo = new ComboBox("Medicament");
		BeanItemContainer<Medicament> medicaments = new BeanItemContainer<>(Medicament.class);
		medicaments.addAll(medicamentService.findAll());
		medicamentCombo.setContainerDataSource(medicaments);
		medicamentCombo.setItemCaptionPropertyId("name");
		fields.bind(medicamentCombo, "medicament");
		addComponent(medicamentCombo);
		medicamentCombo.addValidator(new BeanValidator(Prescription.class, "medicament"));

		// Quantity
		TextField qnt = new TextField("Quantity");
		addComponent(qnt);
		fields.bind(qnt, "quantity");
		qnt.addValidator(new BeanValidator(Prescription.class, "quantity"));

		// Valid from
		DateField validFrom = new DateField("Valid from");
		addComponent(validFrom);
		fields.bind(validFrom, "validFrom");
		validFrom.addValidator(new BeanValidator(Prescription.class, "validFrom"));

		// Valid until
		DateField validUntil = new DateField("Valid until");
		addComponent(validUntil);
		fields.bind(validUntil, "validUntil");
		validUntil.addValidator(new BeanValidator(Prescription.class, "validUntil"));

		// Intervall in hours
		TextField intervallInHours = new TextField("Interval (h)");
		addComponent(intervallInHours);
		fields.bind(intervallInHours, "intervallInHours");
		intervallInHours.addValidator(new BeanValidator(Prescription.class, "intervallInHours"));

		// Comment
		TextArea comment = new TextArea("Comment");
		comment.setNullRepresentation("");
		addComponent(comment);
		fields.bind(comment, "comment");
		comment.addValidator(new BeanValidator(Prescription.class, "comment"));


		// Save the prescription
		Button saveBtn = new Button("save");
		saveBtn.addListener(new Button.ClickListener() {
			public void buttonClick(Button.ClickEvent event) {
				try {
					fields.commit();
					item = new BeanItem(prescriptionService.save(item.getBean()));
					fields.setItemDataSource(item);
				} catch (FieldGroup.CommitException e) {
					// TODO
				}
			}
		});
		addComponent(saveBtn);

		// Delete the prescription
		Button delBtn = new Button("delete");
		delBtn.addListener(new Button.ClickListener() {
			public void buttonClick(Button.ClickEvent event) {
				item.getBean().setDeactivated(true);
				prescriptionService.save(item.getBean());
				item = new BeanItem(new Prescription());
				fields.setItemDataSource(item);
			}
		});
		addComponent(delBtn);
	}
}
