package ch.bfh.bti7081.s2013.yellow.ui;

import ch.bfh.bti7081.s2013.yellow.ui.medication.PrescriptionListView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author rohdj1
 * This is the main view that is displayed once a user has successfully logged in.
 */
public class MainView extends CustomComponent implements View {
	public static final String NAME = "";

	Label text = new Label();

	// go to the prescription list
	Button prescList = new Button("Prescriptions", new Button.ClickListener() {

		@Override
		public void buttonClick(Button.ClickEvent event) {
			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(PrescriptionListView.NAME);
		}
	});
	// Logout button
	Button logout = new Button("Logout", new Button.ClickListener() {

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
	public MainView() {
		setSizeFull();

		// Layout with welcome text, goto presc. and logout button
		VerticalLayout fields = new VerticalLayout(text, prescList, logout);
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
	 * @see View
	 */
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("Hello " + username);
	}
}
