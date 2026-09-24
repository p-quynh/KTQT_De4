package vn.iotstar.service;
import vn.iotstar.model.User_24110316;
import java.sql.SQLException;
import java.util.List;
public interface UserService_24110316 {
 User_24110316 login(String username,String password)throws SQLException;
 User_24110316 find(String username)throws SQLException;
 boolean emailExists(String email)throws SQLException;
 List<User_24110316> page(int page)throws SQLException;
 int count()throws SQLException;
 void create(User_24110316 user,String rawPassword)throws SQLException;
 void update(User_24110316 user,String newPassword)throws SQLException;
 void activate(String username)throws SQLException;
 void delete(String username)throws SQLException;
}
