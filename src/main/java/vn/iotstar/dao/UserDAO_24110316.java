package vn.iotstar.dao;
import vn.iotstar.model.User_24110316;
import java.sql.SQLException;
import java.util.List;
public interface UserDAO_24110316 {
 User_24110316 find(String username) throws SQLException;
 boolean emailExists(String email) throws SQLException;
 boolean emailExistsForOtherUser(String email,String username) throws SQLException;
 List<User_24110316> page(int page,int size) throws SQLException;
 int count() throws SQLException;
 void create(User_24110316 user) throws SQLException;
 void update(User_24110316 user,boolean changePassword) throws SQLException;
 void delete(String username) throws SQLException;
 void activate(String username) throws SQLException;
}
