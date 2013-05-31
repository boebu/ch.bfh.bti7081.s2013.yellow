package ch.bfh.bti7081.s2013.yellow.batch;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;

/**
 * @author Boban Glisovic
 * This Class Represents a JMX-MBean
 * Create Notifications based on existing Prescriptions
 */

public class SystemTrigger {
	private NotificationService notificationService;
	private PrescriptionService presriptionService;

	// Constructer passing the bean-reference for need services (hibernate)
	public SystemTrigger(NotificationService notificationService, PrescriptionService presriptionService) {
		this.notificationService = notificationService;
		this.presriptionService = presriptionService;
	}
    
	// dummy method to test connection to dao
	public long countPrescrptions() {
		return this.presriptionService.countAll();
	}
	
	// default method to create notifications
	public void createNotifications() {
		createNotifications(2);
	}
	
	// create notification based on valid prescriptions
	public void createNotifications(int interval) {
		for(Prescription p :this.presriptionService.findTBD()) {
			notificationService.save(new Notification(p.getPatient().getLinkedUser(),"take this: " + p.getMedicament().getName()));
		}
	}
	
}
