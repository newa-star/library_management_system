package mailSender;

import java.util.Properties;
import java.io.File;
import java.io.PrintWriter;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class SendReminderMail {
	private final String sender = "a09874@126.com";
	private final String password = "IFORTPMYTEXSZWEU";
	private final String host = "smtp.126.com";
	public void sendMail(String bookID) {
		try {
		  String to = "jzhiyuan2021@163.com";
		  Properties properties = new Properties();
	      properties.setProperty("mail.transport.protocol", "smtp");
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.auth", "true");
	      final String smtpPort = "465";// 587 for TLS, 465 for SSL
	      properties.setProperty("mail.smtp.port", smtpPort);
	      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	      properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
	      Session session = Session.getInstance(properties);
	      session.setDebug(true);
	 
	     
	         // create Message 
	         MimeMessage message = new MimeMessage(session);
	         // Set sender, 'Reminder' is the name that will be displayed as sender in recipients' received mail
	         message.setFrom(new InternetAddress(sender, "Reminder", "UTF-8"));
	 
	         // Set To
	         message.setRecipient(Message.RecipientType.TO,
	        		 new InternetAddress(to));
	        
	         // Set Subject:
	         message.setSubject("Book reserved");
	        
	         
	        
	         MimeMultipart multipart = new MimeMultipart();
	         
	         BodyPart text = new MimeBodyPart();
	         text.setContent("One of your reservations is available, see attachments for details.","text/html;charset=UTF-8");
	         // set text
	         multipart.addBodyPart(text);
	         
	         // attachment
	         File file = new File("C:\\Users\\a0987\\Desktop\\glasgow\\course\\graduate project\\myProject\\2650326J-development-project\\src\\mailSender\\reservation.txt");
	         if(!file.exists())
	        	 file.createNewFile();
	         if(file.canRead()&&file.canWrite()) {
	        	 String getReservation = "select UID,bookID,reserved_date,duration from reservation where bookID=?";
	        	 PreparedStatement stmt = connectMysql.Connnector.executePreparedStatement(getReservation);
	        	 stmt.setString(1, bookID);
	        	 ResultSet rs = stmt.executeQuery();
	        	 PrintWriter writer = new PrintWriter(file);
	        	 while(rs.next()){
	        		 writer.print("user: " + rs.getString("UID")+"\n");
	        		 writer.print("book ID: "+ rs.getString("bookID")+"\n");
	        		 writer.print("reserved_date: " + rs.getDate("reserved_date")+"\n");
	        		 writer.print("duration: " + rs.getString("duration")+"\n");
	        	 }
	        	 stmt.close();
	        	 rs.close();
	        	 writer.close();
	         }
	         MimeBodyPart attachment = new MimeBodyPart();
	         DataSource source = new FileDataSource(file);
	         attachment.setDataHandler(new DataHandler(source));
	         attachment.setFileName("reservation.txt");
	         multipart.addBodyPart(attachment);
	         //set type of relation contained in mulitipart, could be related or mixed
	         multipart.setSubType("mixed");
	         message.saveChanges();
	         message.setContent(multipart);
	         //   发送消息
	         Transport transport = session.getTransport();
	         transport.connect(sender,password);
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	      }catch (Exception mex) {
	         mex.printStackTrace();
	      }
	}
}
