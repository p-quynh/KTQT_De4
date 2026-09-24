package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.User_24110316;import vn.iotstar.service.impl.UserServiceImpl_24110316;
@WebServlet("/login") public class LoginController_24110316 extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {WebSupport_24110316.csrf(req.getSession());WebSupport_24110316.view(req,resp,"login.jsp");}
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  try {User_24110316 u=new UserServiceImpl_24110316().login(WebSupport_24110316.trim(req.getParameter("username")),req.getParameter("password"));
   if(u==null){req.setAttribute("error","Tài khoản chưa kích hoạt hoặc thông tin đăng nhập không đúng");WebSupport_24110316.view(req,resp,"login.jsp");return;}
   HttpSession old=req.getSession(false);if(old!=null)old.invalidate();HttpSession s=req.getSession(true);
   // Chỉ lưu thông tin cần cho giao diện; filter lấy vai trò mới nhất từ DB.
   User_24110316 safe=new User_24110316();safe.setUsername(u.getUsername());safe.setFullname(u.getFullname());safe.setAdmin(u.isAdmin());s.setAttribute("authUser",safe);WebSupport_24110316.csrf(s);
   resp.sendRedirect(req.getContextPath()+(u.isAdmin()?"/admin/home":"/home"));
  }catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
}
