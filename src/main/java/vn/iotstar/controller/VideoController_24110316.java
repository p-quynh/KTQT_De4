package vn.iotstar.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.IOException;
import vn.iotstar.model.*;import vn.iotstar.service.impl.*;import java.util.*;
@WebServlet(urlPatterns={"/videos","/videos/detail"}) public class VideoController_24110316 extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
  try {
   VideoServiceImpl_24110316 service=new VideoServiceImpl_24110316();
   if("/videos/detail".equals(req.getServletPath())){
    Video_24110316 video=service.find(WebSupport_24110316.trim(req.getParameter("id")));
    if(video==null){resp.sendError(404,"Không tìm thấy video");return;}
    req.setAttribute("video",video);WebSupport_24110316.view(req,resp,"video/detail.jsp");return;
   }
   List<Category_24110316> categories=new CategoryServiceImpl_24110316().allWithCounts();
   if(categories.isEmpty()){req.setAttribute("categories",categories);WebSupport_24110316.view(req,resp,"video/list.jsp");return;}
   int selected=-1;try{selected=Integer.parseInt(req.getParameter("categoryId"));}catch(Exception ignored){}
   Map<Integer,List<Video_24110316>> videosByCategory=new HashMap<>();
   Map<Integer,Integer> pageByCategory=new HashMap<>(),pagesByCategory=new HashMap<>();
   for(Category_24110316 c:categories){
    int id=c.getCategoryId(),total=Math.max(1,(c.getVideoCount()+2)/3);
    int page=id==selected?Math.min(WebSupport_24110316.page(req.getParameter("page")),total):1;
    videosByCategory.put(id,service.pageByCategory(id,page));pageByCategory.put(id,page);pagesByCategory.put(id,total);
   }
   req.setAttribute("categories",categories);req.setAttribute("videosByCategory",videosByCategory);
   req.setAttribute("pageByCategory",pageByCategory);req.setAttribute("pagesByCategory",pagesByCategory);
   WebSupport_24110316.view(req,resp,"video/list.jsp");
  }catch(Exception ex){WebSupport_24110316.error(req,resp,ex);}
 }
}
