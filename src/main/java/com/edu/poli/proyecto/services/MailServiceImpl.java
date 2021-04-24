package com.edu.poli.proyecto.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.edu.poli.proyecto.model.Mail;



@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
 
    public void sendEmail (Mail mail) {
    	
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
            System.out.println("attach size"+mail.getAttachments().size());
            
            for (int x = 0; x<mail.getAttachments().size();x++ ) {
            	System.out.println("attach"+(String) mail.getAttachments().get(x));
            	FileSystemResource file = new FileSystemResource(new File(mail.getAttachments().get(x).toString()));
            	//ClassPathResource File = new ClassPathResource(mail.getAttachments().get(x).toString());
            	mimeMessageHelper.addAttachment(file.getFilename(), file);
			}
			
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
           
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
       
    }

}
