package vn.iotstar.service.impl;
import vn.iotstar.service.MailService_24110316;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
public class MailServiceImpl_24110316 implements MailService_24110316 {
 public void sendOtp(String email,String code)throws Exception {
  String host=System.getenv("SMTP_HOST"),user=System.getenv("SMTP_USER"),pass=System.getenv("SMTP_PASSWORD");
  if(host==null||user==null||pass==null||host.isBlank()||user.isBlank()||pass.isBlank())throw new IllegalStateException("Thiếu SMTP_HOST, SMTP_USER hoặc SMTP_PASSWORD");
  Properties props=new Properties();props.put("mail.smtp.host",host);props.put("mail.smtp.port",System.getenv().getOrDefault("SMTP_PORT","587"));
  props.put("mail.smtp.auth","true");props.put("mail.smtp.starttls.enable","true");props.put("mail.smtp.starttls.required","true");
  Session session=Session.getInstance(props,new Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(user,pass);}});
  MimeMessage msg=new MimeMessage(session);msg.setFrom(new InternetAddress(user));msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email,false));
  msg.setSubject("Mã xác thực tài khoản KTQT Đề 04","UTF-8");msg.setText("Mã OTP của bạn: "+code+". Mã có hiệu lực 10 phút.","UTF-8");Transport.send(msg);
 }
}
