<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Kích hoạt tài khoản</title></head><body><div class="card form"><h1>Nhập mã OTP</h1><p>Mã có hiệu lực 10 phút; kiểm tra hộp thư của bạn.</p>
<c:if test="${not empty error}"><p class="alert"><c:out value="${error}"/></p></c:if>
<form method="post" action="${pageContext.request.contextPath}/verify-otp"><label for="otp">Mã gồm 6 chữ số</label><input id="otp" name="otp" inputmode="numeric" pattern="[0-9]{6}" maxlength="6" required>
<p><button class="btn" type="submit">Xác nhận</button></p></form></div></body></html>
