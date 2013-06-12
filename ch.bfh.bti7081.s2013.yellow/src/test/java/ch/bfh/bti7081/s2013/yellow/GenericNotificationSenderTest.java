package ch.bfh.bti7081.s2013.yellow;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import ch.bfh.bti7081.s2013.yellow.service.notification.strategy.SendAlarmNotifaction;
import ch.bfh.bti7081.s2013.yellow.ui.medication.IntakeConfirmView;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.TestExecutionListeners;

import java.util.UUID;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Andy Pollari
 * Tests the GenericNotificationSender
 */
@RunWith(MockitoJUnitRunner.class)
public class GenericNotificationSenderTest {

    @Mock
    NotificationService notificationService;

    @Test
    public void testGetContent()
    {
        Notification notification = new Notification();
        String uuid = UUID.randomUUID().toString();
        notification.setUuid(uuid);
        String message = "message";
        notification.setMessage(message);

        String mockReturn = "http://localhost:8080/#!" + IntakeConfirmView.NAME + "/" + uuid;
        when(notificationService.getIntakeConfirmationLink(notification)).thenReturn(mockReturn);

        SendAlarmNotifaction gnst = new SendAlarmNotifaction();
        gnst.setNotificationService(notificationService);
        String expected = "Confirm: http://localhost:8080/#!" + IntakeConfirmView.NAME+ "/" + uuid;

        Assert.assertEquals(expected, gnst.getContent(notification));


    }
}
