package vn.iotstar.service.impl;
import vn.iotstar.dao.UserDAO_24110316;
import vn.iotstar.dao.impl.UserDAOImpl_24110316;
import vn.iotstar.model.User_24110316;
import vn.iotstar.service.*;
import java.sql.SQLException;
import java.util.List;
public class UserServiceImpl_24110316 implements UserService_24110316 {
 private final UserDAO_24110316 dao=new UserDAOImpl_24110316();
 private static void validateProfile(User_24110316 u){
  if(u.getFullname()!=null&&u.getFullname().length()>50)throw new IllegalArgumentException("Họ tên tối đa 50 ký tự");
  if(u.getPhone()!=null&&u.getPhone().length()>15)throw new IllegalArgumentException("Điện thoại tối đa 15 ký tự");
  if(u.getImages()!=null&&u.getImages().length()>500)throw new IllegalArgumentException("URL ảnh tối đa 500 ký tự");
  String email=u.getEmail();
  if(email!=null&&!email.isBlank()&&(email.length()>150||!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")))
   throw new IllegalArgumentException("Email không hợp lệ hoặc vượt quá 150 ký tự");
 }
 public User_24110316 login(String username,String password)throws SQLException {
  User_24110316 u=dao.find(username);
  return u!=null&&u.isActive()&&Passwords_24110316.matches(password,u.getPassword())?u:null;
 }
 public User_24110316 find(String username)throws SQLException{return dao.find(username);}
 public boolean emailExists(String email)throws SQLException{return dao.emailExists(email);}
 public List<User_24110316> page(int page)throws SQLException{return dao.page(page,6);}
 public int count()throws SQLException{return dao.count();}
 public void create(User_24110316 u,String rawPassword)throws SQLException {
  validateProfile(u);
  if(u.getUsername()==null||!u.getUsername().matches("[A-Za-z0-9_.-]{3,50}"))throw new IllegalArgumentException("Username dài 3-50 ký tự, chỉ dùng chữ, số, _ . -");
  if(rawPassword==null||rawPassword.length()<8)throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");
  if(dao.find(u.getUsername())!=null)throw new IllegalArgumentException("Username đã tồn tại");
  if(u.getEmail()!=null&&!u.getEmail().isBlank()&&dao.emailExists(u.getEmail()))throw new IllegalArgumentException("Email đã tồn tại");
  u.setPassword(Passwords_24110316.hash(rawPassword));dao.create(u);
 }
 public void update(User_24110316 u,String newPassword)throws SQLException {
  validateProfile(u);
  if(dao.find(u.getUsername())==null)throw new IllegalArgumentException("User không tồn tại");
  if(u.getEmail()!=null&&!u.getEmail().isBlank()&&dao.emailExistsForOtherUser(u.getEmail(),u.getUsername()))throw new IllegalArgumentException("Email đã tồn tại");
  boolean change=newPassword!=null&&!newPassword.isBlank();
  if(change){if(newPassword.length()<8)throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");u.setPassword(Passwords_24110316.hash(newPassword));}
  dao.update(u,change);
 }
 public void activate(String username)throws SQLException{dao.activate(username);}
 public void delete(String username)throws SQLException{dao.delete(username);}
}
