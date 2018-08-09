package com.psl.utilities;

import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
//import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*import com.sungard.liberty.camel.message.EmailMessageTranslator;
import com.sungard.liberty.camel.message.EmailMessageTranslatorImpl;
import com.sungard.liberty.exception.IPPCommunicationException;*/

public class MailMessageTest {

	
	public static void main(String[] args) {
	      // Recipient's email ID needs to be mentioned.
	      String to = "prateek_shrivastava@persistent.com";

	      // Sender's email ID needs to be mentioned
	      String from = "prateek_shrivastava@persistent.com";

	      final String username = "persistent\\prateek_shrivastava";//change accordingly
	      final String password = "Invasion@008";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.office365.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "MARBIBM.tif";
	         
	     	File f = new File("MARBIBM.tif");
	     	System.out.println("If file exists: "+f.exists());
	     	
	         DataSource source = new FileDataSource(filename);
	         
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);
	         message.setHeader("Content-Type", "multipart/*");

	         System.out.println(message.isMimeType("multipart/*"));
	         System.out.println(multipart.getCount());
	         // Send message - not working - authentication issues
	        // Transport.send(message);

	         //EmailMessageTranslatorImpl em = new EmailMessageTranslatorImpl();
	        /*
	         * This can be added inside ipp service method to produce the output file
	         *  try {
					FileOutputStream fos = new FileOutputStream("MARBIBM.pdf");
					   fos.write(outfile.toByteArray());
					   System.out.println("New File Written");
					   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
					}catch(Exception e){
						e.printStackTrace();
					}*/
	    /*    try {
				em.process(message);
		} catch (IOException e) {
			e.printStackTrace();
			} catch (IPPCommunicationException e) {
				
				e.printStackTrace();
			}*/
	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   }
}
