package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;

import java.util.List;

/**
 * Interface for the NotificationService
 * @author fabianhutzli, bobanglisovic
 *
 */
public interface NotificationService extends GenericService<Notification> {

    /**
     * find new notifications to send
     * @param 
     */
	List<Notification> findNewNotificationsToSend();

    /**
     * sends the Notification
     * @param notification
     */
    void send(Notification notification);

    /**
     *  All new notifications must be send
     * @param
     */
    void sendNotifications();
    
    /**
     * All notifications which wasn't confirmed by the patient must be resend after timePassed
     * @param timePassed in seconds
     */
    void resendNotifications(Integer timePassed);

    /**
     * searches the Notification with the given uuid and confirms the intake of it
     * @param uuid
     *
     * @return notification has been found
     */
    boolean confirmIntake(String uuid);

    /**
     *
     * @param notification
     * @return the link to confirm the confirmation
     */
    String getIntakeConfirmationLink(Notification notification);

	/**
	 * Check and set a notification state
	 * @param notification the notification
	 * @param notificationState the new state
	 * @return was change over successful?
	 */
	boolean setNotificationState(Notification notification, NotificationState notificationState);
}


