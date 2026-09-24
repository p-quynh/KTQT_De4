package vn.iotstar.service.impl;
import vn.iotstar.service.CategoryService_24110316;
import vn.iotstar.dao.impl.CategoryDAOImpl_24110316;
import vn.iotstar.model.Category_24110316;
import java.sql.SQLException;
import java.util.List;
public class CategoryServiceImpl_24110316 implements CategoryService_24110316 {
 public List<Category_24110316> allWithCounts()throws SQLException{return new CategoryDAOImpl_24110316().allWithCounts();}
}
