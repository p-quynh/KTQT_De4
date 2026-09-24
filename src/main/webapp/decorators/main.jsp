<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title><sitemesh:write property="title"/></title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"><sitemesh:write property="head"/></head>
<body><header><div class="shell nav"><a class="brand" href="${pageContext.request.contextPath}/home">VIDEOHUB</a>
<nav><a href="${pageContext.request.contextPath}/home">Trang chủ</a><a href="${pageContext.request.contextPath}/videos">Sản phẩm</a>
<c:choose><c:when test="${empty sessionScope.authUser}"><a href="${pageContext.request.contextPath}/login">Đăng nhập</a><a href="${pageContext.request.contextPath}/register">Đăng ký</a></c:when>
<c:otherwise><c:if test="${sessionScope.authUser.admin}"><a href="${pageContext.request.contextPath}/admin/home">Trang quản trị</a></c:if><span>Xin chào, <c:out value="${sessionScope.authUser.username}"/></span><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></c:otherwise></c:choose></nav></div></header>
<main class="shell"><sitemesh:write property="body"/></main>
<footer><div class="shell">Họ tên: Đặng Nguyễn Minh Khoa &nbsp;·&nbsp; MSSV: 24110316 &nbsp;·&nbsp; Mã đề: 04</div></footer></body></html>
