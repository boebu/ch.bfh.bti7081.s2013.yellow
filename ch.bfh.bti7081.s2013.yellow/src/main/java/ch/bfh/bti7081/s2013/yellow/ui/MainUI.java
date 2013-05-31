package ch.bfh.bti7081.s2013.yellow.ui;

import ch.bfh.bti7081.s2013.yellow.ui.medication.PrescriptionListView;
import ch.bfh.bti7081.s2013.yellow.ui.medication.PrescriptionView;
import ch.bfh.bti7081.s2013.yellow.ui.notification.NotificationListView;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author Janosch Rohdewald
 * Main Main UI class with the navigator
 */
public class MainUI extends UI {

	/**
	 * Init the main Vaadin UI, including navigator and logged checking
	 * @see UI
	 */
	@Override
	protected void init(VaadinRequest request) {

		//
		// Create a new instance of the navigator. The navigator will attach
		// itself automatically to this view.
		//
		new Navigator(this, this);

		//
		// The initial log view where the user can login to the application
		//
		getNavigator().addView(LoginView.NAME, LoginView.class);

		//
		// Add the main view of the application
		//
		getNavigator().addView(HomeView.NAME, HomeView.class);


		getNavigator().addView(PrescriptionListView.NAME, PrescriptionListView.class);
		getNavigator().addView(PrescriptionView.NAME, PrescriptionView.class);

		getNavigator().addView(NotificationListView.NAME, NotificationListView.class);
		//
		// We use a view change handler to ensure the user is always redirected
		// to the login view if the user is not logged in.
		//
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {

				// Check if a user has logged in
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof LoginView;

				if (!isLoggedIn && !isLoginView) {
					// Redirect to login view always if a user has not yet
					// logged in
					getNavigator().navigateTo(LoginView.NAME);
					return false;

				} else if (isLoggedIn && isLoginView) {
					// If someone tries to access to login view while logged in,
					// then cancel
					return false;
				}

				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}
		});
	}
}
