package ch.bfh.bti7081.s2013.yellow.util.stateMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy Pollari
 *         All states for notification are defined as enum
 */
public enum NotificationState {
    NEW, // Notification is new created
    SENT, // Notification has been sent
    MISSED, //
    CONFIRMED(true); // Patient has confirmed the intake of the medicament ;

    // The notification has been closed (e.g. The patient has confirmed his intake)
    private boolean closed;

    private NotificationState(boolean closed) {

        this.closed = closed;
    }

    private NotificationState() {
        this(false);
    }

    /**
     * @return all list of all states which are not closed
     */
    public List<NotificationState> getAllOutstandingStates() {
        NotificationState[] states = NotificationState.values();
        List<NotificationState> filteredStates = new ArrayList<>(states.length);
        for (NotificationState s : states) {
            if (!s.isClosed())
                filteredStates.add(s);
        }
        return filteredStates;
    }

    public boolean isClosed() {
        return closed;
    }

}
