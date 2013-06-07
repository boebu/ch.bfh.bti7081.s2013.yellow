package ch.bfh.bti7081.s2013.yellow.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Janosch Rohdewald
 *         Service to send mails / push messages to pushover
 */
@Service
public class MailService {
	@Autowired
	public AsyncMailSender asyncMailSender;

	/**
	 * Send an email message
	 *
	 * @param to      Receipient(s)  (semicolon separated)
	 * @param replyTo Reply to this addresses (semicolon separated)
	 * @param subject Subject
	 * @param msg     Email content (html)
	 * @throws MailException
	 */
	public void sendMessage(final String to, final String replyTo, final String subject, final String msg) throws MailException {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				message.setFrom("geilename@gmail.com", "yellow Team"); // could be parameterized...
				if (!replyTo.isEmpty())
					message.setReplyTo(replyTo);
				message.setTo(to);
				message.setSubject(subject);
				Map model = new HashMap();
				model.put("content", msg);
				message.setText(msg, true);
			}
		};
		asyncMailSender.send(preparator);
	}

	/**
	 * Send an email message
	 *
	 * @param to      Receipient(s)  (semicolon separated)
	 * @param subject Subject
	 * @param msg     Email content (html)
	 */
	public void sendMessage(final String to, final String subject, final String msg) throws MailException {
		sendMessage(to, "", subject, msg);
	}

}