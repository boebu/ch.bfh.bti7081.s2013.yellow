package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import org.springframework.stereotype.Service;


/**
 * @author Andy Pollari
 * Concrete sendstrategy. Sends an notification as an alarm
 */
@Service
public class SendAlarmNotifaction extends GenericNotificationSender{

    @Override
    String getSubject() {
        return "ALARM";
    }


}
