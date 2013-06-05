package ch.bfh.bti7081.s2013.yellow.model.notification;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.person.Person;
import ch.bfh.bti7081.s2013.yellow.model.person.User;

/**
 * @author Andy Pollari
 * This class represents a notification
 */
@Entity
public class Notification extends YellowEntity<Notification> {

    public NotificationType notificationType;
    @ManyToOne(optional = false)
	@NotNull
    private Person receiver;
    private String message;
    private Date sendat;
    
    //private NotificationState state;
    //private NotificationStateMachine stateMachine;
    public Notification() {
        super(Notification.class);
    }
    public Notification(Person receiver, String message, Date sendat) {
        super(Notification.class);
    	this.receiver = receiver;
        this.message = message;
        this.sendat = sendat;
        //this.state = new NotificationStateNew();
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
    	return sendat;
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
}
