package vn.iotstar.model;
import java.io.Serializable;
public class User_24110316 implements Serializable {
 private String username, password, phone, fullname, email, images;
 private boolean admin, active;
 public String getUsername(){return username;} public void setUsername(String v){username=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getFullname(){return fullname;} public void setFullname(String v){fullname=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getImages(){return images;} public void setImages(String v){images=v;}
 public boolean isAdmin(){return admin;} public void setAdmin(boolean v){admin=v;}
 public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
