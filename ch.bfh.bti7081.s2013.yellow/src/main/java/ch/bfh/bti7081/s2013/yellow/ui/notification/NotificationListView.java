package ch.bfh.bti7081.s2013.yellow.ui.notification;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;
import com.vaadin.annotations.Title;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/* 
 * @author bronc1
 * List of all prescriptions
 * 
*/
@Title("Notification-List")
public class NotificationListView extends CustomComponent implements View {
	NotificationService notificationService;

	public static final String NAME = "notificationList";
	public static final int VISIBLE_ROWS = 10;

	public NotificationListView() {
		// Vaadin & Spring stuff
		SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		notificationService = (NotificationService) springHelper.getBean("notificationService");

		//Table with its columns, you have to give the correct object for every column!
		Table prescrTable = new Table("");
		prescrTable.setHeight(400, Unit.PIXELS);
		prescrTable.addContainerProperty("id", Integer.class, null);
		prescrTable.addContainerProperty("Receiver", String.class, null);
		prescrTable.addContainerProperty("Message", String.class, null);

		
		//Set the number of visible rows, if more, scrollbar will appear
		prescrTable.setPageLength(VISIBLE_ROWS);

		//For every prescription, there's a row in the table
		for (Notification notification : notificationService.findAll()) {
		
			//Reads the data from the prescription and fills it in the rows
			prescrTable.addItem(new Object[]{
					notification.getId(),
					notification.getReceiver().getEmail(),
					notification.getMessage()
			}, notification.getId());
		}

				// The view root layout
		setSizeFull();
		VerticalLayout viewLayout = new VerticalLayout(prescrTable);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(prescrTable, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
