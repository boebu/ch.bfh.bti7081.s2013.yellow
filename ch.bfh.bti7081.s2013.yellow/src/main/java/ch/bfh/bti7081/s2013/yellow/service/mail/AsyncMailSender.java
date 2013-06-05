package ch.bfh.bti7081.s2013.yellow.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;

/**
 * @author Janosch Rohdewald
 * Service class for sending mails asynchronously. It uses the sprint task executor (defined in applicationContext.xml) to send the mails asynchronoulsy.
 * The JavaMailSender class is used to send the mail in the async task.
 */
@Service
public class AsyncMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    private final static int MAX_QUEUE_ADD_TRIES = 5;   // max tries to add a email send task to the task queue
    private final static int WAIT_SECONDS_BETWEEN_TRIES = 2;    // how many seconds to wait until retrying to add the send task to the task queue

    public void send(MimeMessagePreparator preparator) {
        send(new MimeMessagePreparator[]{preparator});
    }

    /**
     * Try to add a new AsyncMailTask to the Spring TaskExecutor queue. Try it MAX_QUEUE_ADD_TRIES times with WAIT_SECONDS_BETWEEN_TRIES seconds between each try.
     *
     * @param preparators
     */
    public void send(MimeMessagePreparator[] preparators) {
        for (int i = 0; i < MAX_QUEUE_ADD_TRIES; i++) {
            if (i > 0)
                try {
                    Thread.sleep(WAIT_SECONDS_BETWEEN_TRIES * 1000);
                } catch (InterruptedException e) {
                }
            try {
                taskExecutor.execute(new AsyncMailTask(preparators));
                break;
            } catch (TaskRejectedException e) {
                if (i == MAX_QUEUE_ADD_TRIES - 1)
                    logFailedMessageSend(preparators, e);
                else
	                System.err.println("Mail send task rejected (TaskRejectedException), trying again in " + WAIT_SECONDS_BETWEEN_TRIES + " seconds.");
            }
        }
    }

    /**
     * Log a message to the log so it could possibly be resent manually.
     *
     * @param preparators Failed messages
     * @param e           Cause
     */
    private void logFailedMessageSend(MimeMessagePreparator[] preparators, Exception e) {
        StringBuilder messageLog = new StringBuilder();
        // If we know which messages failed, log only those
        if (e instanceof MailSendException && ((MailSendException) e).getFailedMessages().size() > 0) {
            for (Object failedMessage : ((MailSendException) e).getFailedMessages().keySet()) {
                messageLog.append("[[");
                if (failedMessage instanceof SimpleMailMessage)
                    messageLog.append(failedMessage.toString());
                else if (failedMessage instanceof MimeMessage)
                    messageLog.append(mimeMessageToString((MimeMessage) failedMessage));
                messageLog.append("]],");
            }
	        System.err.println("Sending mail(s) failed (MailSendException): {{" + messageLog.toString() + "}}");
        }
        // If there wasn't a MailSendException, all messages failed, so log all of them
        for (MimeMessagePreparator preparator : preparators) {
            messageLog.append("[[");
            MimeMessage message = mailSender.createMimeMessage();
            try {
                preparator.prepare(message);
            } catch (Exception e1) {
                messageLog.append("Couldn't decode failed message.");
                System.err.println("Couldn't prepare failed message.");
            }
            messageLog.append(mimeMessageToString(message));
            messageLog.append("]],");
	        System.err.println("Sending mail(s) failed: {{" + messageLog.toString() + "}}");
        }
    }

    /**
     * Convert a MimeMessage to a string, only used for logging
     *
     * @param message Message in the mime format
     * @return message in a string
     */
    private StringBuilder mimeMessageToString(MimeMessage message) {
        try {
            StringBuilder str = new StringBuilder();
            str.append("from=");
            str.append(addressesToString(message.getFrom()));
            str.append(";to=");
            str.append(addressesToString(message.getRecipients(Message.RecipientType.TO)));
            str.append(";cc=");
            str.append(addressesToString(message.getRecipients(Message.RecipientType.CC)));
            str.append(";bcc=");
            str.append(addressesToString(message.getRecipients(Message.RecipientType.BCC)));
            str.append(";replyTo=");
            str.append(addressesToString(message.getReplyTo()));
            str.append(";sentDate=" + message.getSentDate());
            str.append(";subject=" + message.getSubject());
            str.append(";content=" + message.getContent());
            return str;
        } catch (Exception e) {
	        System.err.println("Couldn't decode failed message.");
            return new StringBuilder("Couldn't decode failed message.");
        }
    }

    /**
     * Convert an Address array to a string - used for logging on errors.
     *
     * @param addresses
     * @return
     */
    private StringBuilder addressesToString(Address[] addresses) {
        StringBuilder str = new StringBuilder();
        if (addresses != null)
            for (Address address : addresses)
                str.append(address + ",");
        return str;
    }

	/**
	 * The task that is added to the task executor queue
	 */
    private class AsyncMailTask implements Runnable {

        private MimeMessagePreparator[] preparators;

		/**
		 * Prepare the message to be sent.
		 * @param preparators Messages to be sent.
		 */
        private AsyncMailTask(MimeMessagePreparator[] preparators) {
            this.preparators = preparators;
        }

		/**
		 * Send the email message
		 */
        public void run() {
            try {
                mailSender.send(preparators);
            } catch (MailException e) {
                logFailedMessageSend(preparators, e);
            }
        }
    }
}