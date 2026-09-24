<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Video theo danh mục</title></head><body><h1>Video theo danh mục</h1>
<c:choose><c:when test="${empty categories}"><p>Chưa có danh mục nào.</p></c:when><c:otherwise>
<c:forEach items="${categories}" var="cat"><section class="category-section">
<h2><c:out value="${cat.categoryname}"/> (${cat.videoCount})</h2>
<c:if test="${empty videosByCategory[cat.categoryId]}"><p>Danh mục này chưa có video.</p></c:if>
<div class="grid"><c:forEach items="${videosByCategory[cat.categoryId]}" var="v"><article class="card">
<c:choose><c:when test="${not empty v.poster}"><c:url var="posterUrl" value="${v.poster}"/><img class="poster" src="${posterUrl}" alt="Poster video"></c:when><c:otherwise><div class="poster poster-fallback">▶</div></c:otherwise></c:choose>
<h3><c:out value="${v.title}"/></h3><p>Mã video: <c:out value="${v.videoId}"/><br>Category name: <c:out value="${v.categoryname}"/><br>View: ${v.views}<br>Share (${v.shareCount}) &nbsp; Like (${v.likeCount})</p>
<c:url var="detailLink" value="/videos/detail"><c:param name="id" value="${v.videoId}"/></c:url><a class="btn" href="${detailLink}">Xem chi tiết</a></article></c:forEach></div>
<c:set var="current" value="${pageByCategory[cat.categoryId]}"/><c:set var="last" value="${pagesByCategory[cat.categoryId]}"/>
<nav class="pagination" aria-label="Trang video của danh mục">
<c:if test="${current > 1}"><a aria-label="Trang đầu" href="${pageContext.request.contextPath}/videos?categoryId=${cat.categoryId}&amp;page=1">&laquo;</a><a aria-label="Trang trước" href="${pageContext.request.contextPath}/videos?categoryId=${cat.categoryId}&amp;page=${current - 1}">&lsaquo;</a></c:if>
<c:forEach begin="1" end="${last}" var="p"><c:choose>
<c:when test="${p == pageByCategory[cat.categoryId]}"><strong>${p}</strong></c:when>
<c:otherwise><a href="${pageContext.request.contextPath}/videos?categoryId=${cat.categoryId}&amp;page=${p}">${p}</a></c:otherwise>
</c:choose></c:forEach>
<c:if test="${current < last}"><a aria-label="Trang sau" href="${pageContext.request.contextPath}/videos?categoryId=${cat.categoryId}&amp;page=${current + 1}">&rsaquo;</a><a aria-label="Trang cuối" href="${pageContext.request.contextPath}/videos?categoryId=${cat.categoryId}&amp;page=${last}">&raquo;</a></c:if>
</nav></section></c:forEach>
</c:otherwise></c:choose></body></html>
