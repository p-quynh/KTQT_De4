<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title><c:out value="${video.title}"/></title></head><body><a href="${pageContext.request.contextPath}/videos?categoryId=${video.categoryId}">← Danh sách video</a>
<section class="card detail"><div><c:choose><c:when test="${not empty video.poster}"><c:url var="posterUrl" value="${video.poster}"/><img class="poster" src="${posterUrl}" alt="Poster video"></c:when><c:otherwise><div class="poster poster-fallback">▶</div></c:otherwise></c:choose></div>
<div><h1><c:out value="${video.title}"/></h1><p>Mã video: <c:out value="${video.videoId}"/></p><p>Category name: <c:out value="${video.categoryname}"/></p><p>View: ${video.views}</p><p>Share (${video.shareCount}) &nbsp; Like (${video.likeCount})</p></div></section>
<section class="card"><h2>Mô tả</h2><p><c:out value="${video.description}"/></p></section></body></html>
