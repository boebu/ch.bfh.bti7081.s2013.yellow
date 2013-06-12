package ch.bfh.bti7081.s2013.yellow.ui.medication;

import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import java.io.File;

/**
 * URL: {server}:{port}/#!intakeConfirm
 */
@Title("Intake confirm")
public class IntakeConfirmView extends CustomComponent implements View {

	NotificationService notificationService;


	public static final String NAME = "intakeConfirm";

	public IntakeConfirmView() {
		super();
		SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		notificationService = (NotificationService) springHelper.getBean("notificationService");
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		boolean confirmSuccess = notificationService.confirmIntake(event.getParameters());

		Label okLabel = new Label();
		String basePath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String message;

		if (confirmSuccess) {
			okLabel.setIcon(new FileResource(new File(basePath + "/WEB-INF/images/intakeOk.png")));
			message = "Intake Confirmed";

		} else {
			okLabel.setIcon(new FileResource(new File(basePath + "/WEB-INF/images/intakeNok.png")));
			message = "An error occured. The given intake wasn't found. Maybe the link was wrong.";

		}
		VerticalLayout fields = new VerticalLayout(okLabel);
		// Layout with welcome text, goto presc. and logout button
		fields.setCaption(message);
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		// Center the layout and add a theme
		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);


	}


}
