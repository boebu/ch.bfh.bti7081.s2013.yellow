package ch.bfh.bti7081.s2013.yellow.batch;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author Boban Glisovic
 * This Class Represents a JMX-MBean
 * Create Notifications based on existing Prescriptions
 * Send Notifications
 */

public class SystemTrigger {
	//Load required Services via autowired
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private PrescriptionService presriptionService;

	/**
	 * Count all prescriptions, can be used to test the connection
	 * @return number of all prescriptions
	 */
	public long countPrescrptions() {
		return this.presriptionService.countAll();
	}


	/**
	 *  create notification based on valid prescriptions
	 */
	public void createNotifications() {
		Date today = new Date();
		for(Prescription p :this.presriptionService.findActiveandInRange()) {
			today.setHours(0);
			today.setMinutes(0);
			for(int i=1;i<=(24/p.getIntervallInHours());i++) {
				today.setHours(i*p.getIntervallInHours());
				notificationService.save(new Notification(p.getPatient(),"take this: " + p.getMedicament().getName(),today));
			}
			
		}
	}
	
	/**
	 * send Notifications from stored Notifications
	 */
	public void sendNotifications() {
		notificationService.sendNotifications();
	}

	/**
	 * resend Notifications from stored Notifications if not confirmed
	 */
	public void resendNotifications() {
		notificationService.resendNotifications(1800);
	}
	
}
