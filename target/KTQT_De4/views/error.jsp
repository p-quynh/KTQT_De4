<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="vi"><head><title>Lỗi xử lý</title></head><body><div class="card"><h1>Chưa thực hiện được yêu cầu</h1><p class="alert"><c:out value="${error}"/></p><a href="${pageContext.request.contextPath}/home">Về trang chủ</a></div></body></html>
