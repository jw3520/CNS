package community.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator {
	PasswordAuthentication pa;
	
	public MailAuth() {
		String userName = "mycodetest1234@gmail.com";
		String password = "eebyktuofoxsockg";
		
		pa = new PasswordAuthentication(userName, password);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}