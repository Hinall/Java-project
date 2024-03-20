package com.digitalgis.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.digitalgis.model.Message;


@Component
public class MailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	public Message setEmailTemplate(String subject, String to, List<String> content) throws Exception {
		Message message = new Message();
		message.setTexts(content);
		message.setSubject(subject);
		message.setTo(to);
		return message;
	}

	public void sendMail(Message message) throws Exception {
		new Thread() {
			@Override
			public void run() {
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();

				MimeMessageHelper messageHelper;

				try {
					messageHelper = new MimeMessageHelper(mimeMessage, true);
				} catch (MessagingException e) {
					e.printStackTrace();
					return;
				}

				if (messageHelper != null) {
					try {
						messageHelper.setTo(message.getTo());
						messageHelper.setSubject(message.getSubject());

						if (message.getCc() != null && !message.getCc().isEmpty()) {
							messageHelper.setCc(message.getCc());
						}

						if (message.getBcc() != null && !message.getBcc().isEmpty()) {
							messageHelper.setBcc(message.getBcc());
						}

						messageHelper.setText(message.getTexts().stream().collect(Collectors.joining()), true);

						javaMailSender.send(mimeMessage);

					} catch (MessagingException e) {
						e.printStackTrace();
						return;
					}

				}
			}
		}.start();

	}
	
}
