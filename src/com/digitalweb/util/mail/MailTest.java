package com.digitalweb.util.mail;


public class MailTest {
	public static void main(String[] args) {
		 MailSenderProperties mailInfo = new MailSenderProperties();   
	      mailInfo.setMailServerHost("smtp.163.com");   
	      mailInfo.setMailServerPort("25");   
	      mailInfo.setValidate(true);   
	      mailInfo.setUserName("******@163.com");   
	      mailInfo.setPassword("********");//您的邮箱密码   
	      mailInfo.setFromAddress("*******@163.com");   
	      mailInfo.setToAddress("******@qq.com");   
	      mailInfo.setSubject("测试");   
	      mailInfo.setContent("hahaha");   
	         //这个类主要来发送邮件  
	      SimpleMailSender sms = new SimpleMailSender();  
	      boolean flag = sms.sendTextMail(mailInfo);//发送文体格式   
	      if(flag)
	    	  System.out.println("邮件发送成功！");
//	          sms.sendHtmlMail(mailInfo);//发送html格式  
	}
}
