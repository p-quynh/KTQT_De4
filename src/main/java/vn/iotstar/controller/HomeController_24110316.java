package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
@WebServlet(urlPatterns={"/home","/admin/home"})
public class HomeController_24110316 extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  WebSupport_24110316.csrf(req.getSession());
  if(req.getServletPath().startsWith("/admin/"))WebSupport_24110316.view(req,resp,"admin/home.jsp");
  else WebSupport_24110316.view(req,resp,"home.jsp");
 }
}
