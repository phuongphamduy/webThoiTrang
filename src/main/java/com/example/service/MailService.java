package com.example.service;

import javax.mail.MessagingException;

import com.example.entity.MailInfo;

public interface MailService {
	void send(MailInfo mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mailinfo);
	
	void queue(String to, String subject, String body);
}
