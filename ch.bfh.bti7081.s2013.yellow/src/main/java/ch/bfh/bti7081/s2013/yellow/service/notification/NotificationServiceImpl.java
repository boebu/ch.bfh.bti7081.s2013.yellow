package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.yellow.dao.notification.NotificationDAO;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.model.notification.NotificationType;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import ch.bfh.bti7081.s2013.yellow.service.mail.MailService;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.NotificationContext;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendAlarmNotifaction;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendReminderNotification;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service("notificationService")
public class NotificationServiceImpl extends GenericServiceImpl<Notification> implements NotificationService {

    @Autowired
    NotificationDAO notificationDAO;

    @Autowired
    MailService mailService;

    @PostConstruct
    public void init() {
        setDAO(notificationDAO);
    }

    public static final int TIME_PASSED_DEFAULT = 1800;

    @Override
    public void send(Notification notification) {
        NotificationContext context = new NotificationContext();
        switch (notification.getNotificationType()) {
            case REMINDER:
                context.setSendStrategy(new SendReminderNotification());
                break;
            case ALARM:
                context.setSendStrategy(new SendAlarmNotifaction());
                break;
            default:
                context.setSendStrategy(new SendReminderNotification());
                break;
        }
        context.send(notification);
    }

    @Override
    public void sendNotifications() {
		for(Notification n: findNewNotificitionsToSend()) {
			send(n);
		}
    }
    
    @Override
    public void resendNotifications(Integer timePassed) {

        //If timePassed not set, set it to Default Value
        if (timePassed == null)
            timePassed = TIME_PASSED_DEFAULT;
        //Get every unconfirmed Notifications greater than a specific time passed
        List<Notification> unconfirmedNotifications = findSentNotificationsToResend(timePassed);

        //Set State of those unconfirmed Notifications to deleted
        for (Notification n : unconfirmedNotifications) {
            Notification newNotification = new Notification(n.getReceiver(), n.getMessage(), n.getSendDate());
            newNotification.setParentNotification(n);
            newNotification.setState(NotificationState.NEW);
            newNotification.setNotificationType(NotificationType.REMINDER);
            save(newNotification);

            n.setState(NotificationState.DELETED);
            save(n);

            send(newNotification);
        }
    }

    @Override
    public List<Notification> findSentNotificationsToResend(Integer timePassed) {
        Date cmpDate = new Date();
        cmpDate.setTime(cmpDate.getTime()-timePassed*1000);
        return notificationDAO.findByCriteria(Restrictions.and(
                Restrictions.eq("state", NotificationState.SENT), Restrictions.lt("sendDate", cmpDate)));
    }
    
    @Override
    // Get a list of Notifications of State new and older than than current Time
    public List<Notification> findNewNotificitionsToSend() {
    	Date cmpDate = new Date();
    	return notificationDAO.findByCriteria(Restrictions.and(
                Restrictions.eq("state", NotificationState.NEW), Restrictions.lt("sendDate", cmpDate)));
    }
}
