package com.tao.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {
	private static final String host = "smtp.qq.com";
	private static final String fromMail = "1162165740@qq.com";
	private static final String mailPassword = "1162165740m";
	private String subject;
	private String toMail;
	private Properties props;
	private MimeMessage message;
	private Session session;
	public MailService(){
		props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		subject = "Your commodity from "+fromMail+" !";
		this.session = session;
		this.session= Session.getDefaultInstance(props, new Authenticator() {
       	 
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail,
                       mailPassword);
            }
         });
		
	}
	public void sendMail(String imageUrl,String toMail) throws AddressException, MessagingException{
		this.toMail = toMail;
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromMail));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                toMail));
		message.setSubject(subject);
        // Send message
		 BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("TaoMeiRen,good luck!");
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         messageBodyPart = new MimeBodyPart();
         DataSource source = new FileDataSource(imageUrl);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(imageUrl);
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);
        Transport transport = session.getTransport("smtp");
        transport.send(message);
	}
}
