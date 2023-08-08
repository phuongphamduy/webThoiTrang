package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;
	
	public MailInfo(String to, String subject, String body) {
		this.from = "Phương Phạm <thth1732003@gmail.com>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
