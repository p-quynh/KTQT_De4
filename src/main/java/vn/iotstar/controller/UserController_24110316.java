package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.User_24110316;import vn.iotstar.service.UserService_24110316;import vn.iotstar.service.impl.UserServiceImpl_24110316;
@WebServlet("/admin/users/*") public class UserController_24110316 extends HttpServlet {
 private final UserService_24110316 service=new UserServiceImpl_24110316();
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  try {
   WebSupport_24110316.csrf(req.getSession());
   String action=req.getPathInfo();if(action==null||"/".equals(action))action="/list";
   if("/create".equals(action)){req.setAttribute("editing",false);WebSupport_24110316.view(req,resp,"admin/user-form.jsp");return;}
   if("/edit".equals(action)){User_24110316 u=service.find(req.getParameter("username"));if(u==null){resp.sendError(404);return;}req.setAttribute("user",u);req.setAttribute("editing",true);WebSupport_24110316.view(req,resp,"admin/user-form.jsp");return;}
   if(!"/list".equals(action)){resp.sendError(404);return;}
   int count=service.count(),total=Math.max(1,(count+5)/6),page=Math.min(WebSupport_24110316.page(req.getParameter("page")),total);
   req.setAttribute("users",service.page(page));req.setAttribute("currentPage",page);req.setAttribute("totalPages",total);
   WebSupport_24110316.view(req,resp,"user/list.jsp");
  }catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  String action=req.getPathInfo();
  try {
   if("/delete".equals(action)){
    String username=WebSupport_24110316.trim(req.getParameter("username"));User_24110316 auth=(User_24110316)req.getSession().getAttribute("authUser");
    if(auth.getUsername().equals(username))throw new IllegalArgumentException("Không thể tự xóa tài khoản đang đăng nhập");
    service.delete(username);
   }else if("/create".equals(action)||"/edit".equals(action)){
    User_24110316 u=new User_24110316();u.setUsername(WebSupport_24110316.trim(req.getParameter("username")));
    u.setPhone(WebSupport_24110316.trim(req.getParameter("phone")));u.setFullname(WebSupport_24110316.trim(req.getParameter("fullname")));
    String email=WebSupport_24110316.trim(req.getParameter("email"));u.setEmail(email.isEmpty()?null:email);u.setImages(WebSupport_24110316.trim(req.getParameter("images")));
    u.setAdmin(req.getParameter("admin")!=null);u.setActive(req.getParameter("active")!=null);
    User_24110316 auth=(User_24110316)req.getSession().getAttribute("authUser");
    if("/edit".equals(action)&&auth.getUsername().equals(u.getUsername())&&(!u.isAdmin()||!u.isActive()))
     throw new IllegalArgumentException("Không thể tự bỏ quyền admin hoặc khóa tài khoản đang đăng nhập");
    if("/create".equals(action))service.create(u,req.getParameter("password"));else service.update(u,req.getParameter("password"));
   }else {resp.sendError(404);return;}
   resp.sendRedirect(req.getContextPath()+"/admin/users/list");
  }catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
}
