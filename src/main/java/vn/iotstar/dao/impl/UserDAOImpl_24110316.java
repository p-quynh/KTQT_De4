package vn.iotstar.dao.impl;
import vn.iotstar.connection.DBConnection_24110316;
import vn.iotstar.dao.UserDAO_24110316;
import vn.iotstar.model.User_24110316;
import java.sql.*;
import java.util.*;
public class UserDAOImpl_24110316 implements UserDAO_24110316 {
 private static User_24110316 map(ResultSet rs)throws SQLException {
  User_24110316 u=new User_24110316();u.setUsername(rs.getString("Username"));u.setPassword(rs.getString("Password"));
  u.setPhone(rs.getString("Phone"));u.setFullname(rs.getString("Fullname"));u.setEmail(rs.getString("Email"));
  u.setAdmin(rs.getBoolean("Admin"));u.setActive(rs.getBoolean("Active"));u.setImages(rs.getString("Images"));return u;
 }
 public User_24110316 find(String username)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement("SELECT * FROM dbo.Users WHERE Username=?")){
   p.setString(1,username);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}
  }
 }
 public boolean emailExists(String email)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement("SELECT 1 FROM dbo.Users WHERE Email=?")){
   p.setString(1,email);try(ResultSet r=p.executeQuery()){return r.next();}
  }
 }
 public boolean emailExistsForOtherUser(String email,String username)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement("SELECT 1 FROM dbo.Users WHERE Email=? AND Username<>?")){
   p.setString(1,email);p.setString(2,username);try(ResultSet r=p.executeQuery()){return r.next();}
  }
 }
 public int count()throws SQLException {
  try(Connection c=DBConnection_24110316.open();Statement p=c.createStatement();ResultSet r=p.executeQuery("SELECT COUNT(*) FROM dbo.Users")){r.next();return r.getInt(1);}
 }
 public List<User_24110316> page(int page,int size)throws SQLException {
  String sql="SELECT * FROM dbo.Users ORDER BY Username OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement(sql)){
   p.setInt(1,(page-1)*size);p.setInt(2,size);try(ResultSet r=p.executeQuery()){List<User_24110316> users=new ArrayList<>();while(r.next())users.add(map(r));return users;}
  }
 }
 public void create(User_24110316 u)throws SQLException {
  String sql="INSERT dbo.Users(Username,[Password],Phone,Fullname,Email,[Admin],Active,Images) VALUES(?,?,?,?,?,?,?,?)";
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,u.getUsername());p.setString(2,u.getPassword());p.setString(3,u.getPhone());p.setString(4,u.getFullname());p.setString(5,u.getEmail());
   p.setBoolean(6,u.isAdmin());p.setBoolean(7,u.isActive());p.setString(8,u.getImages());p.executeUpdate();
  }
 }
 public void update(User_24110316 u,boolean changePassword)throws SQLException {
  String sql="UPDATE dbo.Users SET Phone=?,Fullname=?,Email=?,[Admin]=?,Active=?,Images=?"+(changePassword?",[Password]=?":"")+" WHERE Username=?";
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement(sql)){
   p.setString(1,u.getPhone());p.setString(2,u.getFullname());p.setString(3,u.getEmail());p.setBoolean(4,u.isAdmin());p.setBoolean(5,u.isActive());p.setString(6,u.getImages());
   if(changePassword)p.setString(7,u.getPassword());p.setString(changePassword?8:7,u.getUsername());if(p.executeUpdate()==0)throw new SQLException("Không tìm thấy user");
  }
 }
 public void delete(String username)throws SQLException {
  try(Connection c=DBConnection_24110316.open()){
   c.setAutoCommit(false);
   try(PreparedStatement a=c.prepareStatement("DELETE FROM dbo.Shares WHERE Username=?");PreparedStatement b=c.prepareStatement("DELETE FROM dbo.Favorites WHERE Username=?");PreparedStatement d=c.prepareStatement("DELETE FROM dbo.Users WHERE Username=?")){
    a.setString(1,username);a.executeUpdate();b.setString(1,username);b.executeUpdate();d.setString(1,username);if(d.executeUpdate()==0)throw new SQLException("Không tìm thấy user");c.commit();
   }catch(SQLException ex){c.rollback();throw ex;}
  }
 }
 public void activate(String username)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement("UPDATE dbo.Users SET Active=1 WHERE Username=?")){
   p.setString(1,username);if(p.executeUpdate()==0)throw new SQLException("Không tìm thấy user");
  }
 }
}
