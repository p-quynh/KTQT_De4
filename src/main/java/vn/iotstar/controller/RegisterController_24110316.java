package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.User_24110316;import vn.iotstar.service.Passwords_24110316;
import vn.iotstar.service.impl.*;import java.security.SecureRandom;import java.time.Instant;
@WebServlet("/register") public class RegisterController_24110316 extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {WebSupport_24110316.csrf(req.getSession());WebSupport_24110316.view(req,resp,"register.jsp");}
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  try {
   String username=WebSupport_24110316.trim(req.getParameter("username"));String email=WebSupport_24110316.trim(req.getParameter("email"));String pass=req.getParameter("password");
   if(!username.matches("[A-Za-z0-9_.-]{3,50}")||email.length()>150||!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")||pass==null||pass.length()<8)
    throw new IllegalArgumentException("Kiểm tra username, email và mật khẩu (ít nhất 8 ký tự)");
   if(new UserServiceImpl_24110316().find(username)!=null||new UserServiceImpl_24110316().emailExists(email))throw new IllegalArgumentException("Username hoặc email đã được sử dụng");
   String fullname=WebSupport_24110316.trim(req.getParameter("fullname")),phone=WebSupport_24110316.trim(req.getParameter("phone"));
   if(fullname.isBlank()||fullname.length()>50||phone.length()>15)throw new IllegalArgumentException("Họ tên tối đa 50 ký tự, điện thoại tối đa 15 ký tự");
   User_24110316 pending=new User_24110316();pending.setUsername(username);pending.setEmail(email);pending.setFullname(fullname);pending.setPhone(phone);
   pending.setPassword(Passwords_24110316.hash(pass));pending.setActive(true);pending.setAdmin(false);
   String otp=String.format("%06d",new SecureRandom().nextInt(1_000_000));
   new MailServiceImpl_24110316().sendOtp(email,otp);
   HttpSession s=req.getSession();s.setAttribute("pendingUser",pending);s.setAttribute("otpHash",Passwords_24110316.hash(otp));s.setAttribute("otpExpiry",Instant.now().plusSeconds(600).toEpochMilli());s.setAttribute("otpAttempts",0);
   resp.sendRedirect(req.getContextPath()+"/verify-otp");
  }catch(IllegalArgumentException ex){req.setAttribute("error",ex.getMessage());WebSupport_24110316.view(req,resp,"register.jsp");}
   catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
}
