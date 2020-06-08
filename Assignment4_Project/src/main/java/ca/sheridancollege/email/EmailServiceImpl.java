package ca.sheridancollege.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailServiceImpl {

	@Autowired
	private TemplateEngine templateEngine;
	
	
	@Autowired
	public JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to,String subject,String text) {
		
		SimpleMailMessage message= new SimpleMailMessage();
		
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);}
	
	public void sendMailWithInline(String name,String messageBody,String footer,String to,String subject) throws MessagingException{
		
		
		//Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("name", name);
		ctx.setVariable("message", messageBody);
		ctx.setVariable("footer", footer);
		
		//Prepare the message using the spring helper
		final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		final MimeMessageHelper message=
					new MimeMessageHelper(mimeMessage,true,"UTF-8"); // true = multiport
		message.setSubject(subject);
		message.setFrom("emailJavaExample@gmail.com");
		message.setTo(to);
		
		// create the html body using thymeleaf
		final String htmlContent = this.templateEngine.process("emailTemplate.html", ctx);
		message.setText(htmlContent,true); // true=isHTML
		
		//Send mail
		this.emailSender.send(mimeMessage);
		
		
		
		
		
		
		
		
	}
	
	
}
