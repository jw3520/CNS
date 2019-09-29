package community.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {
    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;
    
    //날짜 타입 변경
	public static String dateTypeChange(String str_date, String pattern) {
		if(str_date == null || str_date.equals("")) {
			return null;
		}
		
		if(pattern.equals("hh:mm:ss")) {
			str_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " " + str_date;
			pattern = "yyyy-MM-dd " + pattern;
		} else if(pattern.equals("hh:mm")) {
			str_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " " + str_date;
			pattern = "yyyy-MM-dd " + pattern;
		} else if(pattern.equals("MM-dd")) {
			str_date = new SimpleDateFormat("yyyy").format(new Date()) + "-" + str_date;
			pattern = "yyyy-MM-dd";
		} else if(pattern.equals("MM.dd")) {
			str_date = new SimpleDateFormat("yyyy").format(new Date()) + "." + str_date;
			pattern = "yyyy.MM.dd";
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		String result = null;
		
		try {
			date = format.parse(str_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long curTime = System.currentTimeMillis();	// 현재 시간
		long regTime = date.getTime();				// 입력받은 시간
		long diffTime = (curTime - regTime) / 1000;	// 시간 차
		
		if (diffTime < SEC) {
			result = "방금 전";
		} else if ((diffTime /= SEC) < MIN) {
			result = diffTime + "분 전";
		} else if ((diffTime /= MIN) < HOUR) {
			result = (diffTime) + "시간 전";
		} else if ((diffTime /= HOUR) < DAY) {
			result = (diffTime) + "일 전";
		} else if ((diffTime /= DAY) < MONTH) {
			result = (diffTime) + "달 전";
		} else {
			result = (diffTime)/MONTH + "년 전";
		}
		return result;
	}
	
	//날짜 타입 변경(영문표기에서 숫자표기로)
	public static String dateTypeChange2(Date date) {
		if(date == null) {
			return " ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	//이메일 전송
	public static void postMail(String recipient, String subject, String message, String from) throws MessagingException, UnsupportedEncodingException {
		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "gmail-smtp.l.google.com");
        //props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(props, new MailAuth());
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("admin@jungwoo.shop", from, "UTF-8"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=UTF-8");
        
        Transport.send(msg);
    }
	
	//게시판 추천수 case
	public static int voteCase(int up_count, int down_count) {
		int total_count = up_count + down_count;
		int caseNum = 0;
		
		if(total_count >= 10) {
			//굵기 변화
			if (total_count < 50) { //vote의 합이 50 미만(굵기 변화 없음)
				caseNum += 10;
			} else if (total_count < 100) { //vote의 합이 100 미만(굵기 bold)
				caseNum += 20;
			} else { //vote의 합이 100 미만(굵기 bolder)
				caseNum += 30;
			}
			
			//색깔 변화
			if(up_count > down_count && up_count/down_count >= 2) { //추천 다수(색깔 red)
				caseNum += 1;
			} else if (up_count < down_count && down_count/up_count >= 2) { //반대 다수(색깔 blue)
				caseNum += 2;
			} else { //추천 vs 반대 차이가 2배 미만(색깔 purple)
				caseNum += 3;
			}
		}
		return caseNum;
	}
}