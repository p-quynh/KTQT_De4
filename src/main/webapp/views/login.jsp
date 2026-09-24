<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Đăng nhập</title></head><body><div class="card form"><h1>Đăng nhập</h1>
<c:if test="${not empty error}"><p class="alert"><c:out value="${error}"/></p></c:if>
<c:if test="${not empty sessionScope.notice}"><p class="alert success"><c:out value="${sessionScope.notice}"/></p><c:remove var="notice" scope="session"/></c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
<label for="username">Username</label><input id="username" name="username" required maxlength="50" autocomplete="username">
<label for="password">Mật khẩu</label><input id="password" name="password" type="password" required autocomplete="current-password">
<p><button class="btn" type="submit">Đăng nhập</button></p></form><p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p></div></body></html>
