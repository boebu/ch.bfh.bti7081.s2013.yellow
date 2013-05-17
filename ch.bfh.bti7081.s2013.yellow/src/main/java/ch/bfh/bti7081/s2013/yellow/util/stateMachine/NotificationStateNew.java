package ch.bfh.bti7081.s2013.yellow.util.stateMachine;

public class NotificationStateNew implements NotificationState {

	@Override
	public NotificationState send() {
		// TODO write notification into queue
		return new NotificationStateSent();
	}


}
