package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.mail.MailService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Andy Pollari
 *         Generic Notification sender
 */
public abstract class GenericNotificationSender implements SendNotificationStrategy {

	@Autowired
	private MailService mailService;

    @Autowired
    private NotificationService notificationService;

	/**
	 * Send a notification, default is with receiver email, subject from subclass and message from notification
	 * @param notification
	 */
	public void sendNotification(Notification notification) {
		mailService.sendMessage(notification.getReceiver().getEmail(), getSubject(), notification.getMessage());
	}

	abstract String getSubject();

    public String getContent(Notification notification) {
        String link = "<a href='" + notificationService.getIntakeConfirmationLink(notification) + "'> confirm Link </a>";
        return "Confirm: " + notificationService.getIntakeConfirmationLink(notification);
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
