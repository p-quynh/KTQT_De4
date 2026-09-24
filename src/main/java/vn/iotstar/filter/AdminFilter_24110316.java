package vn.iotstar.filter;
import jakarta.servlet.*;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.User_24110316;import vn.iotstar.dao.impl.UserDAOImpl_24110316;
public class AdminFilter_24110316 implements Filter {
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException {
  HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;HttpSession s=req.getSession(false);
  User_24110316 auth=s==null?null:(User_24110316)s.getAttribute("authUser");
  if(auth==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
  try {User_24110316 db=new UserDAOImpl_24110316().find(auth.getUsername());
   if(db==null||!db.isAdmin()||!db.isActive()){s.invalidate();resp.sendError(403);return;}
  }catch(Exception e){throw new ServletException("Không thể kiểm tra quyền admin",e);}
  if("POST".equalsIgnoreCase(req.getMethod())){
   String expected=(String)s.getAttribute("csrfToken"),actual=req.getParameter("csrfToken");
   if(expected==null||!expected.equals(actual)){resp.sendError(403,"Token biểu mẫu không hợp lệ");return;}
  }
  chain.doFilter(request,response);
 }
}
