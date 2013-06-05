package ch.bfh.bti7081.s2013.yellow.ui;

import ch.bfh.bti7081.s2013.yellow.ui.medication.PrescriptionListView;
import ch.bfh.bti7081.s2013.yellow.ui.notification.NotificationListView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

import java.io.File;

/**
 * @author rohdj1
 *         This is the main view that is displayed once a user has successfully logged in.
 */
public class HomeView extends CustomComponent implements View {
	public static final String NAME = "";

	Label text = new Label("",ContentMode.HTML);

	// go to the prescription list
	Link prescList = new Link(" Prescription list", new ExternalResource("#!" + PrescriptionListView.NAME));
	Link ntfyList = new Link(" Notification list", new ExternalResource("#!" + NotificationListView.NAME));
	// Logout button
	Button logout = new Button(" Logout", new Button.ClickListener() {

		@Override
		public void buttonClick(Button.ClickEvent event) {

			// "Logout" the user
			getSession().setAttribute("user", null);

			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(NAME);
		}
	});

	/**
	 * Init the main view
	 */
	public HomeView() {
		setSizeFull();

		String basePath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		prescList.setIcon(new FileResource(new File(basePath+"/WEB-INF/images/prescription.png")));
		ntfyList.setIcon(new FileResource(new File(basePath+"/WEB-INF/images/notification.png")));

		// Layout with welcome text, goto presc. and logout button
		VerticalLayout fields = new VerticalLayout(text, prescList, ntfyList, logout);
		fields.setCaption("Main navigation");
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

	/**
	 * Update the currently logged in user
	 *
	 * @see View
	 */
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("<b>Hello " + username+"</b>");
	}
}
