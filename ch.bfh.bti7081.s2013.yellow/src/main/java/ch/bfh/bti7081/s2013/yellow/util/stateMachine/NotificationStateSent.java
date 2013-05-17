package ch.bfh.bti7081.s2013.yellow.util.stateMachine;

public class NotificationStateSent implements NotificationState {

	@Override
	public NotificationState send() {
		// TODO check whether Notification has been confirmed
		return new NotificationStateConfirmed();
	}



}
