package vn.iotstar.controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
final class WebSupport_24110316 {
 private WebSupport_24110316(){}
 static void view(HttpServletRequest req,HttpServletResponse resp,String path)throws ServletException,IOException {req.getRequestDispatcher("/views/"+path).forward(req,resp);}
 static int page(String raw){try{return Math.max(1,Math.min(100000,Integer.parseInt(raw)));}catch(Exception ex){return 1;}}
 static String trim(String raw){return raw==null?"":raw.trim();}
 static void error(HttpServletRequest req,HttpServletResponse resp,Exception ex)throws ServletException,IOException{
  req.setAttribute("error",ex instanceof SQLException?"Lỗi cơ sở dữ liệu. Kiểm tra kết nối, cấu trúc bảng và log Tomcat.":ex.getMessage());
  req.getServletContext().log("Lỗi xử lý yêu cầu",ex);view(req,resp,"error.jsp");
 }
 static String csrf(HttpSession session){String token=(String)session.getAttribute("csrfToken");if(token==null){token=UUID.randomUUID().toString();session.setAttribute("csrfToken",token);}return token;}
}
