import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationState;
import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateMachine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Andy Pollari
 * This class tests out Statemachine for the Notification states
 */
public class NotificationStateMachineTest {
    NotificationStateMachine nsm = new NotificationStateMachine();

    /**
     * Tests all outgoing states for the state new
     */
    @Test
    public void testStateFom_NEW(){
        // New -> Sent: OK
        String invalidOutgoingState = "This outgoing state is not valid ";
        assertTrue(invalidOutgoingState + NotificationState.SENT,
                nsm.validChangeOver(NotificationState.NEW, NotificationState.SENT));
        // New -> New: NOK
        assertFalse(nsm.validChangeOver(NotificationState.NEW, NotificationState.NEW));
        // New -> Confirmed: NOK
        assertFalse(nsm.validChangeOver(NotificationState.NEW, NotificationState.CONFIRMED));
        // New -> Missed: False
        assertFalse(nsm.validChangeOver(NotificationState.NEW, NotificationState.MISSED));
    }

    @Test
    public void testNumberOfOutgoingStates_NEW(){
        checkNumberOfOutgoingStates(NotificationState.NEW, 1);
    }

    @Test
    public void testStateFom_SENT(){
        assertFalse(nsm.validChangeOver(NotificationState.SENT, NotificationState.NEW));
        assertFalse(nsm.validChangeOver(NotificationState.SENT, NotificationState.SENT));
        assertTrue(nsm.validChangeOver(NotificationState.SENT, NotificationState.CONFIRMED));
        assertTrue(nsm.validChangeOver(NotificationState.SENT, NotificationState.MISSED));
    }

    @Test
    public void testNumberOfOutgoingStates_SENT(){
        checkNumberOfOutgoingStates(NotificationState.SENT, 2);
    }

    @Test
    public void testStateFrom_CONFIRMED()
    {
        assertFalse(nsm.validChangeOver(NotificationState.CONFIRMED, NotificationState.NEW));
        assertFalse(nsm.validChangeOver(NotificationState.CONFIRMED, NotificationState.SENT));
        assertFalse(nsm.validChangeOver(NotificationState.CONFIRMED, NotificationState.CONFIRMED));
        assertFalse(nsm.validChangeOver(NotificationState.CONFIRMED, NotificationState.MISSED));
    }

    @Test
    public void testNumberOfOutgoingStates_CONFIRMED(){
        checkNumberOfOutgoingStates(NotificationState.CONFIRMED, 0);
    }

    @Test
    public void testStateFrom_MISSED()
    {
        assertFalse(nsm.validChangeOver(NotificationState.MISSED, NotificationState.NEW));
        assertFalse(nsm.validChangeOver(NotificationState.MISSED, NotificationState.SENT));
        assertFalse(nsm.validChangeOver(NotificationState.MISSED, NotificationState.CONFIRMED));
        assertFalse(nsm.validChangeOver(NotificationState.MISSED, NotificationState.MISSED));
    }

    @Test
    public void testNumberOfOutgoingStates_MISSED(){
        checkNumberOfOutgoingStates(NotificationState.MISSED, 0);
    }

    @Test
    public void testAllStatesHasDefinedOutgoingStates()
    {
        for (NotificationState n : NotificationState.values())
        {
            assertFalse("For " + n + " is no outgoing state defined",
                    nsm.getAllOutgoingStates(n) == null);
        }
    }

    /**
     *
     * @param stateToCheck
     * @param expectedNumberOfOutgoingNotifications
     */
    private void checkNumberOfOutgoingStates(NotificationState stateToCheck, int expectedNumberOfOutgoingNotifications)
    {
        int count = 0;
        for (NotificationState n : NotificationState.values())
        {
            if (nsm.validChangeOver(stateToCheck, n))
                count++;
        }
        assertEquals(expectedNumberOfOutgoingNotifications, count);
    }
}
