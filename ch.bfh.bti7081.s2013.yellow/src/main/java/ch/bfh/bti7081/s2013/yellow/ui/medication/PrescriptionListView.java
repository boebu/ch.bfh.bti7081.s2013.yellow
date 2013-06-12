package ch.bfh.bti7081.s2013.yellow.ui.medication;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

import java.io.File;
import java.sql.Timestamp;

/* 
 * @author bronc1
 * List of all prescriptions
 * 
*/
@Title("Prescription-List")
public class PrescriptionListView extends CustomComponent implements View {
	PrescriptionService prescriptionService;

	public static final String NAME = "prescriptionList";
	public static final int VISIBLE_ROWS = 10;

	public PrescriptionListView() {
		// Vaadin & Spring stuff
		SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		prescriptionService = (PrescriptionService) springHelper.getBean("prescriptionService");

		// Find the application directory
		String basepath = VaadinService.getCurrent()
				.getBaseDirectory().getAbsolutePath();

		// Images as a file resource
		FileResource resourceEdit = new FileResource(new File(basepath +
				"/WEB-INF/images/edit.png"));
		FileResource resourceAdd = new FileResource(new File(basepath +
				"/WEB-INF/images/add.png"));
		FileResource resourceBack = new FileResource(new File(basepath +
				"/WEB-INF/images/back.png"));
		FileResource resourceComment = new FileResource(new File(basepath +
				"/WEB-INF/images/comment.png"));

		//Table with its columns, you have to give the correct object for every column!
		Table prescrTable = new Table("");
		prescrTable.addContainerProperty("Patient", String.class, null);
		prescrTable.addContainerProperty("Medicament", String.class, null);
		prescrTable.addContainerProperty("Quantity", Integer.class, null);
		prescrTable.addContainerProperty("Date created", Timestamp.class, null);
		prescrTable.addContainerProperty("Last update date", Timestamp.class, null);
		prescrTable.addContainerProperty("Last update from", String.class, null);
		prescrTable.addContainerProperty("Valid from", Timestamp.class, null);
		prescrTable.addContainerProperty("Valid until", Timestamp.class, null);
		prescrTable.addContainerProperty("Interval in h", Integer.class, null);
		prescrTable.addContainerProperty("Hover for comment", Link.class, null);
		prescrTable.addContainerProperty("Edit", Link.class, null);
		
		//Set the number of visible rows, if more, scrollbar will appear
		prescrTable.setPageLength(VISIBLE_ROWS);

		//For every prescription, there's a row in the table
		for (Prescription prescriptions : prescriptionService.findActive()) {

			// Edit-Link -> Prescription-Site with id
			Link linkEdit = new Link(null,
					new ExternalResource("#!"+ PrescriptionView.NAME +"/"+prescriptions.getId()));
			linkEdit.setIcon(resourceEdit);
			
			// Comment -> Handled as hover-text
			// ...unfortunately not useful for tablets/smartphones -> Maybe leaving out later -> visible via "edit"
			Link linkComment = new Link(null,
					new ExternalResource("#!"+ PrescriptionView.NAME +"/"+prescriptions.getId()));
			linkComment.setIcon(resourceComment);
			linkComment.setDescription(prescriptions.getComment());

			// Delete-Link -> To delete a prescription, click on edit then delete from there
			
			//Reads the data from the prescription and fills it in the rows
			prescrTable.addItem(new Object[]{
					prescriptions.getPatient().getName() + " " +
							prescriptions.getPatient().getVorname(),
					prescriptions.getMedicament().getName(),
					prescriptions.getQuantity(),
					prescriptions.getCreation(),
					prescriptions.getLastUpdated(),
					"TODO",//TODO: Link to Username
					prescriptions.getValidFrom(),
					prescriptions.getValidUntil(),
					prescriptions.getIntervallInHours(),
					linkComment,
					linkEdit
			}, prescriptions.getId());
		}

		// New prescription-Link
		Link linkNewPresc = new Link(null,
				new ExternalResource("#!"+ PrescriptionView.NAME));
		linkNewPresc.setIcon(resourceAdd);
		linkNewPresc.setCaption(" Add a new prescription");
		
		// Back to homepage-Link
		Link linkBack = new Link(null,
				new ExternalResource("#!"));
		linkBack.setIcon(resourceBack);
		linkBack.setCaption(" Back to homepage");
		
		// The view root layout
		setSizeFull();
		VerticalLayout viewLayout = new VerticalLayout(prescrTable);
		VerticalLayout btnLayout = new VerticalLayout();
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(prescrTable, Alignment.MIDDLE_CENTER);
		btnLayout.addComponent(linkNewPresc);
		btnLayout.setComponentAlignment(linkNewPresc, Alignment.MIDDLE_CENTER);
		btnLayout.addComponent(linkBack);
		btnLayout.setComponentAlignment(linkBack, Alignment.MIDDLE_CENTER);
		viewLayout.addComponent(btnLayout);
		viewLayout.setComponentAlignment(btnLayout, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}
	
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
