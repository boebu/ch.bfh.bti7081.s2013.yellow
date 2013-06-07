package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.yellow.dao.notification.NotificationDAO;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.NotificationContext;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendAlarmNotifaction;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendReminderNotification;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("notificationService")
public class NotificationServiceImpl extends GenericServiceImpl<Notification> implements NotificationService {

    @Autowired
    NotificationDAO notificationDAO;

    @PostConstruct
    public void init() {
        setDAO(notificationDAO);
    }

    @Override
    public void send(Notification notification) {
        NotificationContext context = new NotificationContext();
        switch (notification.notificationType){
        switch (notification.getNotificationType) {
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
    public void resendNotifications(Integer timePassed) {

        //If timePassed not set, set it to Default Value
        if (timePassed == null)
            timePassed = TIME_PASSED_DEFAULT;

        //todo

        //Get every unconfirmed Notifications greater than a specific time passed
        List<Notification> unconfirmedNotifications = findSentNotificationsToResend(timePassed);

        //Set State of those unconfirmed Notifications to deleted
        for (Notification deletedNotification:unconfirmedNotifications)       {
            deletedNotification.setState(NotificationState.DELETED);
            //Create new Notification

        }



    }

    @Override
    public List<Notification> findSentNotificationsToResend(Integer timePassed) {

        return null;
        //Liefert die Notifications zurück, welche den Status SENT haben und das SENTDATE + timePassed < now().
        //return notificationDAO.findByCriteria(Restrictions.and(
         //       Restrictions.eq("state", NotificationState.SENT), Restrictions.lt("sendDate", now - timePassed)));


    }
}
