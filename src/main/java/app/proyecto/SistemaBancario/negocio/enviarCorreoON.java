package app.proyecto.SistemaBancario.negocio;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class enviarCorreoON {
	
	
	
		
		public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		    
		    String remitente = "6zhimi6@gmail.com";  
		    String clave="12345";

		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", "smtp.gmail.com");  
		    props.put("mail.smtp.user", "6zhimi6@gmail.com");
		    props.put("mail.smtp.clave", "12345");  
		    props.put("mail.smtp.auth", "true");    
		    props.put("mail.smtp.starttls.enable", "true"); 
		    props.put("mail.smtp.port", "587"); 

		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);

		    try {
		        message.setFrom(new InternetAddress(remitente));
		        message.addRecipients(Message.RecipientType.TO, destinatario);
		        message.setSubject(asunto);
		        message.setText(cuerpo);
		        Transport transport = session.getTransport("smtp");
		        transport.connect("smtp.gmail.com", remitente, clave);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
		    }
		    catch (MessagingException me) {
		        me.printStackTrace(); 
		        System.out.print("errorrr email..."  + me.getMessage());
		    }
		}
	}

