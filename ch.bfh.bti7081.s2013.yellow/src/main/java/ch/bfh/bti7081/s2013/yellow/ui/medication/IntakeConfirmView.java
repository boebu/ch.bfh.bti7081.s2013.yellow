package ch.bfh.bti7081.s2013.yellow.ui.medication;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;

/**
 * URL: {server}:{port}/#!intakeConfirm
 */
@Title("Intake confirm")
public class IntakeConfirmView extends CustomComponent implements View {

	public static final String NAME = "intakeConfirm";

	public IntakeConfirmView() {
		super();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
