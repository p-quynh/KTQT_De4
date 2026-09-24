package vn.iotstar.service;
import vn.iotstar.model.Video_24110316;
import java.sql.SQLException;
import java.util.List;
public interface VideoService_24110316 {
 Video_24110316 find(String id)throws SQLException;
 List<Video_24110316> pageByCategory(int categoryId,int page)throws SQLException;
 int countByCategory(int categoryId)throws SQLException;
}
