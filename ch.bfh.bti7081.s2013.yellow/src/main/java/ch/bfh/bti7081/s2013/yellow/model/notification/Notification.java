package ch.bfh.bti7081.s2013.yellow.model.notification;


import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import ch.bfh.bti7081.s2013.yellow.model.person.Person;
import ch.bfh.bti7081.s2013.yellow.model.person.User;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateMachine;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private NotificationStateMachine notificationStateMachine;

    public NotificationType notificationType;

    @ManyToOne(optional = false)
	@NotNull
    private Person receiver;
    private String message;
    private NotificationState state;
    private Date sendDate;

    private String intakeConfirmLink;

    @OneToOne(mappedBy = "parentNotification")
    private Notification parentNotification;

    //private NotificationState state;
    //private NotificationStateMachine stateMachine;
    public Notification(Person receiver, String message, Date sendDate) {
        super(Notification.class);
    	this.receiver = receiver;
        this.message = message;
        this.sendDate = sendDate;
        this.intakeConfirmLink = UUID.randomUUID().toString();
    }

    public Notification(User receiver, String message, NotificationState state, Notification parent) {
        super(Notification.class);
        this.receiver = receiver;
        this.message = message;
        this.state = state;
        this.parentNotification = parent;
        this.intakeConfirmLink = UUID.randomUUID().toString();
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

    public String getIntakeConfirmLink() {
        return intakeConfirmLink;
    }

    public void setIntakeConfirmLink(String intakeConfirmLink) {
        this.intakeConfirmLink = intakeConfirmLink;
    }
}
