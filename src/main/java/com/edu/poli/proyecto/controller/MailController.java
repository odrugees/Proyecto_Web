package com.edu.poli.proyecto.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poli.proyecto.model.Mail;
import com.edu.poli.proyecto.services.MailService;


 
@RestController
@CrossOrigin(origins = "*")
public class MailController {
 
	@Autowired
	private MailService notificationService;
	
	@RequestMapping("/api/correo")
	public String sendEmail(@RequestBody Mail mail){
		notificationService.sendEmail(mail);
		return "Email sent successfully";
	}
	
}