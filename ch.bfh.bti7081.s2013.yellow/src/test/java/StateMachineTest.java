import ch.bfh.bti7081.s2013.yellow.util.stateMachine.NotificationStateMachine;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Andy Pollari
 * This class tests out Statemachine for the Notification states
 */
public class StateMachineTest {
    NotificationStateMachine nsm;

    @BeforeClass
    public void init(){
        nsm = new NotificationStateMachine();
    }

    @Test
    public void testStateFom_NEW(){

        assertTrue(true);
    }
}
