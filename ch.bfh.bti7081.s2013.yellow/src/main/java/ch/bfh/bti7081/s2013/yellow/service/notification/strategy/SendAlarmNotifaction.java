package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;


/**
 * @author Andy Pollari
 * Concrete sendstrategy. Sends an notification as an alarm
 */
public class SendAlarmNotifaction extends GenericNotificationSender implements SendNotificationStrategy{
    
    @Override
    String getSubject() {
        return "ALARM";
    }


}
