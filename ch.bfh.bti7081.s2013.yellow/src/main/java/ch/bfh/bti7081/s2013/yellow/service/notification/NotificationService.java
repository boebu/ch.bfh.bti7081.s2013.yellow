package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;

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
     */
    void confirmIntake(String uuid);
}


