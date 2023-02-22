package com.policy.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 
 */
public class SendingMail2 {

	public void run(String response, String visaNumber) throws Exception {

		int counter = 1;
		System.out.println("MessageBody -------------> " + visaNumber);
		Message message = new MimeMessage(getSession());
		String recipients = Utility2.getPropStringValues("mail.to");
		String[] list = recipients.split(",");
		message.addFrom(new InternetAddress[] { new InternetAddress(Utility2.getPropStringValues("mail.from")) });
		if (response != null && !"".equals(response)
				&& "Policy Validator Trademark Erros".toString().equals(response)) {
			message.addRecipient(RecipientType.TO, new InternetAddress(Utility2.getPropStringValues("mail.toShape")));

		} else {
			if (list != null) {
				for (String address : list) {
					message.addRecipient(RecipientType.TO, new InternetAddress(address));
				}
			}
		}

		message.addRecipient(RecipientType.BCC, new InternetAddress(Utility2.getPropStringValues("mail.from")));

		message.setSubject(response + " " + Utility2.getPropStringValues("mail.subject"));

		message.setContent(visaNumber, "text/plain");
		try {
			Transport.send(message);
			counter = counter + 1;
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private Session getSession() {

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", Utility2.getPropStringValues("mail.smtp.host"));
		properties.setProperty("mail.smtp.port", Utility2.getPropStringValues("mail.smtp.port"));

		return Session.getInstance(properties, null);
	}

}
