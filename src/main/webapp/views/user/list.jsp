<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Quản lý Users</title></head><body><h1>Quản lý Users</h1><p><a class="btn" href="${pageContext.request.contextPath}/admin/users/create">Thêm User</a></p>
<div class="card"><table><thead><tr><th>Username</th><th>Họ tên</th><th>Email</th><th>Điện thoại</th><th>Admin</th><th>Active</th><th>Thao tác</th></tr></thead><tbody>
<c:forEach items="${users}" var="u"><tr><td><c:out value="${u.username}"/></td><td><c:out value="${u.fullname}"/></td><td><c:out value="${u.email}"/></td><td><c:out value="${u.phone}"/></td><td>${u.admin ? 'Có' : 'Không'}</td><td>${u.active ? 'Có' : 'Không'}</td><td>
<c:url var="editLink" value="/admin/users/edit"><c:param name="username" value="${u.username}"/></c:url><a href="${editLink}">Sửa</a>
<form method="post" action="${pageContext.request.contextPath}/admin/users/delete" onsubmit="return confirm('Xóa user này?')"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="username" value="<c:out value='${u.username}'/>"><button class="btn danger" type="submit">Xóa</button></form>
</td></tr></c:forEach></tbody></table></div>
<nav class="pagination" aria-label="Phân trang người dùng">
<c:if test="${currentPage > 1}"><a aria-label="Trang đầu" href="${pageContext.request.contextPath}/admin/users/list?page=1">&laquo;</a><a aria-label="Trang trước" href="${pageContext.request.contextPath}/admin/users/list?page=${currentPage - 1}">&lsaquo;</a></c:if>
<c:forEach begin="1" end="${totalPages}" var="p"><c:choose><c:when test="${p == currentPage}"><strong>${p}</strong></c:when><c:otherwise><a href="${pageContext.request.contextPath}/admin/users/list?page=${p}">${p}</a></c:otherwise></c:choose></c:forEach>
<c:if test="${currentPage < totalPages}"><a aria-label="Trang sau" href="${pageContext.request.contextPath}/admin/users/list?page=${currentPage + 1}">&rsaquo;</a><a aria-label="Trang cuối" href="${pageContext.request.contextPath}/admin/users/list?page=${totalPages}">&raquo;</a></c:if>
</nav>
</body></html>
