package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.cs1_task4.Notification;
import ch.bfh.bti7081.s2013.yellow.dao.notification.NotificationDAO;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.NotificationContext;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendAlarmNotifaction;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendReminderNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("notificationService")
public class NotificationServiceImpl extends GenericServiceImpl<Notification> implements NotificationService {

    @Autowired
    NotificationDAO notificationDAO ;

	@PostConstruct
	public void init(){
		setDAO(notificationDAO);
	}

    @Override
    public void send(Notification notification)
    {
        NotificationContext context = new NotificationContext();
        switch (notification.getNotificationType){
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
}
