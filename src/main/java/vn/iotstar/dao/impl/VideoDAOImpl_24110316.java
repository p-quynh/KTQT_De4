package vn.iotstar.dao.impl;
import vn.iotstar.connection.DBConnection_24110316;
import vn.iotstar.dao.VideoDAO_24110316;
import vn.iotstar.model.Video_24110316;
import java.sql.*;
import java.util.*;
public class VideoDAOImpl_24110316 implements VideoDAO_24110316 {
 private static final String FIELDS="SELECT v.VideoId,v.Title,v.Poster,v.Views,v.[Description],v.Active,v.CategoryId,c.Categoryname,"
  +"(SELECT COUNT(*) FROM dbo.Shares s WHERE s.VideoId=v.VideoId) shareCount,"
  +"(SELECT COUNT(*) FROM dbo.Favorites f WHERE f.VideoId=v.VideoId) likeCount "
  +"FROM dbo.Videos v JOIN dbo.Category c ON c.CategoryId=v.CategoryId WHERE v.Active=1 AND c.Status=1 ";
 private static Video_24110316 map(ResultSet r)throws SQLException {
  Video_24110316 v=new Video_24110316();v.setVideoId(r.getString("VideoId"));v.setTitle(r.getString("Title"));v.setPoster(r.getString("Poster"));v.setViews(r.getInt("Views"));v.setDescription(r.getString("Description"));v.setActive(r.getBoolean("Active"));v.setCategoryId(r.getInt("CategoryId"));v.setCategoryname(r.getString("Categoryname"));v.setShareCount(r.getInt("shareCount"));v.setLikeCount(r.getInt("likeCount"));return v;
 }
 public Video_24110316 find(String id)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement(FIELDS+"AND v.VideoId=?")){
   p.setString(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}
  }
 }
 public List<Video_24110316> pageByCategory(int categoryId,int page,int size)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement(FIELDS+"AND v.CategoryId=? ORDER BY v.VideoId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY")){
   p.setInt(1,categoryId);p.setInt(2,(page-1)*size);p.setInt(3,size);try(ResultSet r=p.executeQuery()){List<Video_24110316> list=new ArrayList<>();while(r.next())list.add(map(r));return list;}
  }
 }
 public int countByCategory(int categoryId)throws SQLException {
  try(Connection c=DBConnection_24110316.open();PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM dbo.Videos v JOIN dbo.Category c ON c.CategoryId=v.CategoryId WHERE v.CategoryId=? AND v.Active=1 AND c.Status=1")){
   p.setInt(1,categoryId);try(ResultSet r=p.executeQuery()){r.next();return r.getInt(1);}
  }
 }
}
