<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>${editing ? 'Sửa User' : 'Thêm User'}</title></head><body><div class="card form"><h1>${editing ? 'Sửa User' : 'Thêm User'}</h1>
<form method="post" action="${pageContext.request.contextPath}/admin/users/${editing ? 'edit' : 'create'}"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
<label>Username</label><input name="username" value="<c:out value='${user.username}'/>" maxlength="50" required ${editing ? 'readonly' : ''}>
<label>Mật khẩu ${editing ? '(để trống nếu không đổi)' : ''}</label><input name="password" type="password" minlength="8" ${editing ? '' : 'required'} autocomplete="new-password">
<label>Họ tên</label><input name="fullname" value="<c:out value='${user.fullname}'/>" maxlength="50">
<label>Email</label><input name="email" type="email" value="<c:out value='${user.email}'/>" maxlength="150">
<label>Điện thoại</label><input name="phone" value="<c:out value='${user.phone}'/>" maxlength="15">
<label>Ảnh (URL)</label><input name="images" value="<c:out value='${user.images}'/>" maxlength="500">
<div class="checks"><label><input name="admin" type="checkbox" ${user.admin ? 'checked' : ''}> Admin</label><label><input name="active" type="checkbox" ${user.active ? 'checked' : ''}> Active</label></div>
<p><button class="btn" type="submit">Lưu</button> <a class="btn secondary" href="${pageContext.request.contextPath}/admin/users/list">Quay lại</a></p></form></div></body></html>
