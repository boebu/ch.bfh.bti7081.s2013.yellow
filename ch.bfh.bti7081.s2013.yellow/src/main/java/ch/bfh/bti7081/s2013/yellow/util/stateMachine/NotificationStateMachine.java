package ch.bfh.bti7081.s2013.yellow.util.stateMachine;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState.*;


/**
 * This is the StateMachine Class for the Notifications.
 * Because of using the framework Hibernate, it is not practical to use the common State Pattern.
 * That is why we decided to implement the StateMachine in this class.
 * @author Andy Pollari
 */
@Service
public class NotificationStateMachine {

	Map<NotificationState, List<NotificationState>> mapping;

    /**
     * initializes all states
     */
    public NotificationStateMachine() {
        mapping = new HashMap<>();

        List<NotificationState> newValues = new ArrayList<>();
        List<NotificationState> sentValues = new ArrayList<>();
        List<NotificationState> confirmedValues = new ArrayList<>();
        List<NotificationState> missedValues = new ArrayList<>();


        newValues.add(SENT);

        sentValues.add(CONFIRMED);
        sentValues.add(MISSED);

        this.mapping = new HashMap<>();
		this.mapping.put(NEW, newValues);
        this.mapping.put(SENT, sentValues);
        this.mapping.put(CONFIRMED, confirmedValues);
        this.mapping.put(MISSED, missedValues);
	}

    /**
     *
     * @param before
     * @param after
     * @return
     */
    public boolean validChangeOver(NotificationState before, NotificationState after)
    {
        List<NotificationState> validNextStates = mapping.get(before);
        return validNextStates.contains(after);
    }

    public List<NotificationState> getAllOutgoingStates(NotificationState state)
    {
        return this.mapping.get(state);
    }
}
