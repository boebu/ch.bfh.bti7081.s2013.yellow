package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import org.springframework.stereotype.Service;


/**
 * @author Andy Pollari
 * Concrete sendstrategy. Sends a notification as a reminder.
 */
@Service
public class SendReminderNotification extends GenericNotificationSender  {

    @Override
    String getSubject() {
        return "Erinnerung";
    }

}
