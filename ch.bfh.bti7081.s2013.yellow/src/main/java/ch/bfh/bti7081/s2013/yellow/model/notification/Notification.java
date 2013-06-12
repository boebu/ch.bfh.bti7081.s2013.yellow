package ch.bfh.bti7081.s2013.yellow.model.notification;


import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import ch.bfh.bti7081.s2013.yellow.model.person.Person;
import ch.bfh.bti7081.s2013.yellow.model.person.User;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateMachine;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Andy Pollari
 * This class represents a notification
 */
@Entity
public class Notification extends YellowEntity<Notification> {

    @Autowired
    @Transient
    private NotificationStateMachine notificationStateMachine;


	@Enumerated(EnumType.ORDINAL)
	@NotNull
    public NotificationType notificationType;

    @ManyToOne(optional = false)
	@NotNull
    private Person receiver;
    private String message;

	@NotNull
    private NotificationState state;

    private Date sendDate;

    @OneToOne
    private Notification parentNotification;

    
    //private NotificationState state;
    //private NotificationStateMachine stateMachine;
    public Notification(Person receiver, String message, Date sendDate) {
        super(Notification.class);
    	this.receiver = receiver;
        this.message = message;
        this.sendDate = sendDate;
        this.state = NotificationState.NEW;
	    this.notificationType = NotificationType.REMINDER;
    }

    public Notification(User receiver, String message, NotificationState state, Notification parent) {
        super(Notification.class);
        this.receiver = receiver;
        this.message = message;
        this.state = state;
        this.parentNotification = parent;
        //this.state = new NotificationStateNew();
    }

    public Notification() {
        super(Notification.class);
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public void send()
    {
    	//state = state.send();
    }
    
    


    public NotificationType getGetNotificationType() {
        return notificationType;
    }

    public void setGetNotificationType(NotificationType getNotificationType) {
        this.notificationType = getNotificationType;
    }

    public Person getReceiver() {
        return receiver;
    }
    
    public Date getSendDate() {
    	return sendDate;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationState getState() {
        return state;
    }

    public void setState(NotificationState state) {

        if (!notificationStateMachine.validChangeOver(getState(), state))
            throw new IllegalStateException("Illegal NotificationState change over");
        this.state = state;
    }

    public Notification getParentNotification() {
        return parentNotification;
    }

    public void setParentNotification(Notification parentNotification) {
        this.parentNotification = parentNotification;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
