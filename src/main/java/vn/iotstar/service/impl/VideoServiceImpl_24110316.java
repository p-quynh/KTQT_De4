package vn.iotstar.service.impl;
import vn.iotstar.service.VideoService_24110316;
import vn.iotstar.dao.impl.VideoDAOImpl_24110316;
import vn.iotstar.model.Video_24110316;
import java.sql.SQLException;
import java.util.List;
public class VideoServiceImpl_24110316 implements VideoService_24110316 {
 private final VideoDAOImpl_24110316 dao=new VideoDAOImpl_24110316();
 public Video_24110316 find(String id)throws SQLException{return dao.find(id);}
 public List<Video_24110316> pageByCategory(int categoryId,int page)throws SQLException{return dao.pageByCategory(categoryId,page,3);}
 public int countByCategory(int categoryId)throws SQLException{return dao.countByCategory(categoryId);}
}
