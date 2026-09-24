package vn.iotstar.dao;
import vn.iotstar.model.Video_24110316;
import java.sql.SQLException;
import java.util.List;
public interface VideoDAO_24110316 {
 Video_24110316 find(String id) throws SQLException;
 List<Video_24110316> pageByCategory(int categoryId,int page,int size) throws SQLException;
 int countByCategory(int categoryId) throws SQLException;
}
