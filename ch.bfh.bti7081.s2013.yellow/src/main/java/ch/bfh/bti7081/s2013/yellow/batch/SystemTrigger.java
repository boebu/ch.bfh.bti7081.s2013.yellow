package ch.bfh.bti7081.s2013.yellow.batch;

import java.util.Calendar;
import java.util.Date;

import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.mail.MailService;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

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
	@Autowired
	private MailService mailService;
    
	// dummy method to test connection to dao
	public long countPrescrptions() {
		return this.presriptionService.countAll();
	}
	
	
	// create notification based on valid prescriptions
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
	
	// send Notifications from stored Notifications 
	public void sendNotifications() {
		notificationService.sendNotifications();
	}
	
	// send Notifications from stored Notifications 
	public void resendNotifications() {
		notificationService.resendNotifications(1800);
	}
	
}
