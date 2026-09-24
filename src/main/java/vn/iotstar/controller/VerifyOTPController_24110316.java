package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.User_24110316;import vn.iotstar.service.Passwords_24110316;
import vn.iotstar.dao.impl.UserDAOImpl_24110316;
@WebServlet("/verify-otp") public class VerifyOTPController_24110316 extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  if(req.getSession().getAttribute("pendingUser")==null){resp.sendRedirect(req.getContextPath()+"/register");return;}
  WebSupport_24110316.view(req,resp,"verify.jsp");
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  HttpSession s=req.getSession();User_24110316 pending=(User_24110316)s.getAttribute("pendingUser");String hash=(String)s.getAttribute("otpHash");Long expiry=(Long)s.getAttribute("otpExpiry");
  if(pending==null||hash==null||expiry==null||System.currentTimeMillis()>expiry){clear(s);req.setAttribute("error","Phiên OTP đã hết hạn. Hãy đăng ký lại.");WebSupport_24110316.view(req,resp,"register.jsp");return;}
  int attempts=(Integer)s.getAttribute("otpAttempts");
  if(attempts>=5){clear(s);req.setAttribute("error","Đã nhập sai quá số lần. Hãy đăng ký lại.");WebSupport_24110316.view(req,resp,"register.jsp");return;}
  if(!Passwords_24110316.matches(WebSupport_24110316.trim(req.getParameter("otp")),hash)){
   s.setAttribute("otpAttempts",attempts+1);req.setAttribute("error","OTP không đúng");WebSupport_24110316.view(req,resp,"verify.jsp");return;
  }
  try {new UserDAOImpl_24110316().create(pending);clear(s);s.setAttribute("notice","Kích hoạt thành công. Hãy đăng nhập.");resp.sendRedirect(req.getContextPath()+"/login");}
  catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
 private static void clear(HttpSession s){s.removeAttribute("pendingUser");s.removeAttribute("otpHash");s.removeAttribute("otpExpiry");s.removeAttribute("otpAttempts");}
}
