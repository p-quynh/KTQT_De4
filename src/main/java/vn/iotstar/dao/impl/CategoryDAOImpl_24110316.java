package vn.iotstar.dao.impl;
import vn.iotstar.connection.DBConnection_24110316;
import vn.iotstar.dao.CategoryDAO_24110316;
import vn.iotstar.model.Category_24110316;
import java.sql.*;
import java.util.*;
public class CategoryDAOImpl_24110316 implements CategoryDAO_24110316 {
 public List<Category_24110316> allWithCounts()throws SQLException {
  String sql="SELECT c.CategoryId,c.Categoryname,c.Categorycode,c.Images,c.Status,COUNT(v.VideoId) videoCount FROM dbo.Category c LEFT JOIN dbo.Videos v ON v.CategoryId=c.CategoryId AND v.Active=1 WHERE c.Status=1 GROUP BY c.CategoryId,c.Categoryname,c.Categorycode,c.Images,c.Status ORDER BY c.CategoryId";
  try(Connection c=DBConnection_24110316.open();Statement p=c.createStatement();ResultSet r=p.executeQuery(sql)){
   List<Category_24110316> list=new ArrayList<>();while(r.next()){Category_24110316 x=new Category_24110316();x.setCategoryId(r.getInt("CategoryId"));x.setCategoryname(r.getString("Categoryname"));x.setCategorycode(r.getString("Categorycode"));x.setImages(r.getString("Images"));x.setStatus(r.getBoolean("Status"));x.setVideoCount(r.getInt("videoCount"));list.add(x);}return list;
  }
 }
}
