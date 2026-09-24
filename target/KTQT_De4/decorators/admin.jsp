<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title><sitemesh:write property="title"/></title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"><sitemesh:write property="head"/></head>
<body><header class="admin-header"><div class="shell nav"><a class="brand" href="${pageContext.request.contextPath}/admin/home">QUẢN TRỊ VIDEOHUB</a>
<nav><a href="${pageContext.request.contextPath}/home">Trang chủ</a><a href="${pageContext.request.contextPath}/videos">Sản phẩm</a><a href="${pageContext.request.contextPath}/admin/home">Trang quản trị</a><a href="${pageContext.request.contextPath}/admin/users/list">Users</a><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></nav></div></header>
<main class="shell"><sitemesh:write property="body"/></main><footer><div class="shell">Họ tên: Đặng Nguyễn Minh Khoa &nbsp;·&nbsp; MSSV: 24110316 &nbsp;·&nbsp; Mã đề: 04</div></footer></body></html>
