package ca.sheridancollege.controller;



import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.email.EmailServiceImpl;

@Controller
public class EmailController {

	@Autowired
	EmailServiceImpl esi;
	
	@GetMapping("/sendemail")
	public String sendEmail(@RequestParam String email, @RequestParam String message)
	{
		esi.sendSimpleMessage(email,"Real Estate Services",message);
		
		return "email/home.html";
	}
	@GetMapping("/email")
	public String goEmail() {
		return "realtor/email.html";
	}
}