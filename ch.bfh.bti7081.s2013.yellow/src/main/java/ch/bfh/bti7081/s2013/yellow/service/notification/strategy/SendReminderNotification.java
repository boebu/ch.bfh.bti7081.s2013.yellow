package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;


/**
 * @author Andy Pollari
 * Concrete sendstrategy. Sends a notification as a reminder.
 */
public class SendReminderNotification extends GenericNotificationSender implements SendNotificationStrategy  {

    @Override
    String getSubject() {
        return "Erinnerung";
    }
}
