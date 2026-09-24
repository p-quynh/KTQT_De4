package vn.iotstar.connection;
import jakarta.servlet.ServletContextEvent;import jakarta.servlet.ServletContextListener;import jakarta.servlet.annotation.WebListener;
import vn.iotstar.model.User_24110316;import vn.iotstar.dao.impl.UserDAOImpl_24110316;
import vn.iotstar.service.Passwords_24110316;
@WebListener public class AdminBootstrap_24110316 implements ServletContextListener {
 public void contextInitialized(ServletContextEvent event){
  String name=System.getenv("ADMIN_USERNAME"),pass=System.getenv("ADMIN_PASSWORD");
  if(name==null||pass==null||name.isBlank()||pass.length()<8)return;
  try {UserDAOImpl_24110316 dao=new UserDAOImpl_24110316();if(dao.find(name)==null){
   User_24110316 u=new User_24110316();u.setUsername(name);u.setPassword(Passwords_24110316.hash(pass));u.setFullname("Quản trị viên");u.setAdmin(true);u.setActive(true);dao.create(u);
  }}catch(Exception e){event.getServletContext().log("Không thể khởi tạo admin. Kiểm tra SQL Server và DB_*",e);}
 }
}
