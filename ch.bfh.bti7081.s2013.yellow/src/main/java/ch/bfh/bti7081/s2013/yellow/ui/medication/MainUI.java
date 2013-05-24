package ch.bfh.bti7081.s2013.yellow.ui.medication;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

/**
 * @author Janosch Rohdewald
 * Main UI class with the tabs
 */
public class MainUI extends UI {

		private TabSheet tabsheet;
	/**
	 * @see  com.vaadin.ui.UI
	 */
	@Override
	protected void init(VaadinRequest request) {
		tabsheet = new TabSheet();
		setContent(tabsheet);

		tabsheet.addTab(new PrescriptionUI(), "Prescriptions");
		tabsheet.addTab(new PrescriptionUI(), "Notifications");
		tabsheet.addTab(new PrescriptionUI(), "...");

	}
}
