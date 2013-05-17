package ch.bfh.bti7081.s2013.yellow.model.notification;

import java.util.HashMap;
import java.util.Map;

import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateClarificated;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateConfirmed;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateMissed;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateNew;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateSent;

public class NotificationStateMachine {

	Map<Class<? extends NotificationState>, Class<? extends NotificationState>> mapping = new HashMap<>();

	public void NotificationStateMachine() {
		this.mapping = new HashMap<>();
		this.mapping.put(NotificationStateNew.class,
				NotificationStateSent.class);
		this.mapping.put(NotificationStateSent.class,
				NotificationStateConfirmed.class);
		this.mapping.put(NotificationStateSent.class,
				NotificationStateMissed.class);
		this.mapping.put(NotificationStateMissed.class,
				NotificationStateClarificated.class);

	}

	public NotificationState handle(NotificationState currentState,
			NotificationState nextRequested) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}

		return currentState;
	}

}