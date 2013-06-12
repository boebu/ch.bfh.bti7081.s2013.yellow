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
import java.util.UUID;

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

    private String uuid;

    @OneToOne
    private Notification parentNotification;

    public Notification(Person receiver, String message, Date sendDate) {
        super(Notification.class);
    	this.receiver = receiver;
        this.message = message;
        this.sendDate = sendDate;
        this.uuid = UUID.randomUUID().toString();
        this.state = NotificationState.NEW;
	    this.notificationType = NotificationType.REMINDER;
    }

    public Notification(User receiver, String message, NotificationState state, Notification parent) {
        super(Notification.class);
        this.receiver = receiver;
        this.message = message;
        this.state = state;
        this.parentNotification = parent;
        this.uuid = UUID.randomUUID().toString();
    }

    public Notification() {
        super(Notification.class);
    }

    public NotificationType getGetNotificationType() {
        return notificationType;
    }

    public void setGetNotificationType(NotificationType getNotificationType) {
        this.notificationType = getNotificationType;
    }


    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendDate() {
    	return sendDate;
    }

    public Person getReceiver() {
        return receiver;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
