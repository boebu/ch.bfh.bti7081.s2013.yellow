package ch.bfh.bti7081.s2013.yellow.ui;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Vaadin sample / rohdj1
 *         This is a simple view to login into the application. Currently it's just checking if username and password are admin/admin.
 */
public class LoginView extends CustomComponent implements View,
		Button.ClickListener {

	public static final String NAME = "login";

	private final TextField user;

	private final PasswordField password;

	private final Button loginButton;

	/**
	 * Add the components to the login view
	 */
	public LoginView() {
		setSizeFull();

		// Create the user input field
		user = new TextField("User:");
		user.setWidth("300px");
		user.setRequired(true);
		user.setInputPrompt("Your username (eg. joe@email.com)");
		user.setInvalidAllowed(false);

		// Create the password input field
		password = new PasswordField("Password:");
		password.setWidth("300px");
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		// Create login button
		loginButton = new Button("Login", this);
		loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// Add both to a panel
		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Please login to access the application. (admin/admin)");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		// The view root layout
		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}

	/**
	 * @see com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
	 */
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {

		// focus the username field when user arrives to the login view
		user.focus();
	}

	/**
	 * Validator for validating the passwords
	 */
	private static final class PasswordValidator extends
			AbstractValidator<String> implements Validator {

		public PasswordValidator() {
			super("The password provided is not valid");
		}

		@Override
		protected boolean isValidValue(String value) {
			//
			// Password must be at least 8 characters long and contain at least
			// one number
			//
			if (value != null
					&& (value.length() < 8 || !value.matches(".*\\d.*"))) {
				return false;
			}
			return true;
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}
	}

	/**
	 * Validate the fields using the navigator.
	 *
	 * @see com.vaadin.ui.Button.ClickEvent
	 */
	@Override
	public void buttonClick(Button.ClickEvent event) {
		// By using validors for the fields we reduce the amount of queries we have to use to the database
		// for wrongly entered passwords
		if (!user.isValid() || !password.isValid()) {
			return;
		}

		String username = user.getValue();
		String password = this.password.getValue();

		//
		// Validate username and password with database here. For examples sake
		// I use a dummy username and password.
		//
		boolean isValid = username.equals("admin")
				&& password.equals("admin");

		if (isValid) {
			// Store the current user in the service session
			getSession().setAttribute("user", username);

			// Navigate to main view
			getUI().getNavigator().navigateTo(HomeView.NAME);

		} else {

			// Wrong password clear the password field and refocuses it
			this.password.setValue(null);
			this.password.focus();
		}
	}
}