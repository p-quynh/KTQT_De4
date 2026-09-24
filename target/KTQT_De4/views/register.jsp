<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Đăng ký</title></head><body><div class="card form"><h1>Đăng ký tài khoản</h1><p class="muted">Mã kích hoạt sẽ được gửi qua email.</p>
<c:if test="${not empty error}"><p class="alert"><c:out value="${error}"/></p></c:if>
<form method="post" action="${pageContext.request.contextPath}/register">
<label for="username">Username</label><input id="username" name="username" required minlength="3" maxlength="50" pattern="[A-Za-z0-9_.-]+">
<label for="fullname">Họ tên</label><input id="fullname" name="fullname" maxlength="50" required>
<label for="email">Email</label><input id="email" name="email" type="email" maxlength="150" required>
<label for="phone">Điện thoại</label><input id="phone" name="phone" maxlength="15">
<label for="password">Mật khẩu</label><input id="password" name="password" type="password" minlength="8" required autocomplete="new-password">
<p><button class="btn" type="submit">Gửi OTP</button></p></form></div></body></html>
