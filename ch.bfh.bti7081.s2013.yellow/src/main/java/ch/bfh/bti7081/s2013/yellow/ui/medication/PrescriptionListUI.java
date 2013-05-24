package ch.bfh.bti7081.s2013.yellow.ui.medication;

import java.io.File;

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
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/* List of all prescriptions
 * 
 * @author bronc1
*/
@Title("Prescription-List")
public class PrescriptionListUI extends UI {
	PrescriptionService prescriptionService;
	MedicamentService medicamentService;
	PatientService patientService;

	private FieldGroup fields = new FieldGroup();
	private FormLayout form = new FormLayout();
	private BeanItem<Prescription> item;
	Prescription prescription;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		prescriptionService = (PrescriptionService)springHelper.getBean("prescriptionService");
		medicamentService = (MedicamentService)springHelper.getBean("medicamentService");
		patientService = (PatientService)springHelper.getBean("patientService");


		setContent(form);   // Set the form as the whole page content
			prescription = prescriptionService.findById(0);

		// Search for the prescription
		try {
			int prescriptionId = Integer.valueOf(vaadinRequest.getParameter("prescId"));
			prescription = prescriptionService.findById(prescriptionId);
		} catch (NumberFormatException e) {
		}
		if (prescription == null)
			prescription = new Prescription();

		// Set the prescription as the data source
		item = new BeanItem(prescription);
		fields.setItemDataSource(item);

		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resourceEdit = new FileResource(new File(basepath +
				"/WEB-INF/images/edit.png"));
		
		//Table with its columns
		Table prescrTable = new Table("");
		prescrTable.addContainerProperty("Patient", String.class, null);
		prescrTable.addContainerProperty("Medicament", String.class, null);
		prescrTable.addContainerProperty("Interval in h", Integer.class, null);
		prescrTable.addContainerProperty("Edit", Link.class, null);

		//For every prescription, there's a row in the table
		for (Prescription prescriptions : prescriptionService.findAll()){

			// Edit-Link -> Prescription-Site with id
			Link linkEdit = new Link(null,
					        new ExternalResource("http://localhost:8080?prescid=" + prescriptions.getId()));
			linkEdit.setIcon(resourceEdit);
			
			// Delete-Link -> To delete a prescription, click on edit then delete from there
			
			//Reads the data from the prescription and fills it in the rows
			prescrTable.addItem(new Object[] {
					prescriptions.getPatient().getName() + " " + 
							prescriptions.getPatient().getVorname(), prescriptions.getMedicament().getName(), 
							prescriptions.getIntervallInHours(), linkEdit
			}, prescriptions.getId());
		}
			
		
		form.addComponent(prescrTable);
	}
}
