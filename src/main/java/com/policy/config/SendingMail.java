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
public class SendingMail {

	public void run(String response, String visaNumber) throws Exception {

		int counter = 1;
		System.out.println("MessageBody -------------> " + visaNumber);
		Message message = new MimeMessage(getSession());
		String recipients = Utility.getPropStringValues("mail.to");
		String[] list = recipients.split(",");
		message.addFrom(new InternetAddress[] { new InternetAddress(Utility.getPropStringValues("mail.from")) });
		if (response != null && !"".equals(response)
				&& "Policy Validator Trademark Erros".toString().equals(response)) {
			message.addRecipient(RecipientType.TO, new InternetAddress(Utility.getPropStringValues("mail.toShape")));

		} else {
			if (list != null) {
				for (String address : list) {
					message.addRecipient(RecipientType.TO, new InternetAddress(address));
				}
			}
		}

		message.addRecipient(RecipientType.BCC, new InternetAddress(Utility.getPropStringValues("mail.from")));

		message.setSubject(response + " " + Utility.getPropStringValues("mail.subject"));

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
		properties.setProperty("mail.smtp.host", Utility.getPropStringValues("mail.smtp.host"));
		properties.setProperty("mail.smtp.port", Utility.getPropStringValues("mail.smtp.port"));

		return Session.getInstance(properties, null);
	}

}
