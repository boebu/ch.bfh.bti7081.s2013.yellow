package ch.bfh.bti7081.s2013.yellow.ui;

import ch.bfh.bti7081.s2013.yellow.ui.medication.PrescriptionListView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

public class MainView extends CustomComponent implements View {
	public static final String NAME = "";

	Label text = new Label();

	Button prescList = new Button("Prescriptions", new Button.ClickListener() {

		@Override
		public void buttonClick(Button.ClickEvent event) {
			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(PrescriptionListView.NAME);
		}
	});
	Button logout = new Button("Logout", new Button.ClickListener() {

		@Override
		public void buttonClick(Button.ClickEvent event) {

			// "Logout" the user
			getSession().setAttribute("user", null);

			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(NAME);
		}
	});

	public MainView() {
//		setCompositionRoot(new CssLayout(text, prescList, logout));
		setSizeFull();
		VerticalLayout fields = new VerticalLayout(text, prescList, logout);
		fields.setCaption("Main navigation");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("Hello " + username);
	}
}
