<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký tài khoản</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }
            .form-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
            }
            .form-group input:focus {
                border-color: #007bff;
                outline: none;
            }
            .form-actions {
                text-align: center;
                margin-top: 20px;
            }
            .form-actions input[type="submit"], .form-actions input[type="reset"] {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                margin-right: 10px;
            }
            .form-actions input[type="submit"] {
                background-color: #007bff;
                color: #fff;
            }
            .form-actions input[type="submit"]:hover {
                background-color: #0056b3;
            }
            .form-actions input[type="reset"] {
                background-color: #6c757d;
                color: #fff;
            }
            .form-actions input[type="reset"]:hover {
                background-color: #5a6268;
            }
            .login-link {
                text-align: center;
                margin-top: 20px;
            }
            .login-link a {
                color: #007bff;
                text-decoration: none;
            }
            .login-link a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container">
            <h1>Đăng ký tài khoản</h1>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert" style="color: red; margin-bottom: 20px;">
                    ${errorMessage}
                </div>
            </c:if>

            <form action="LoginURL" method="post">
                <input type="hidden" name="service" value="registerCustomer">

                <div class="form-group">
                    <label for="full_name">Họ tên</label>
                    <input type="text" id="full_name" name="full_name" value="<%= request.getParameter("full_name") != null ? request.getParameter("full_name") : "" %>">
                </div>

                <div class="form-group">
                    <label for="address">Địa chỉ</label>
                    <input type="text" id="address" name="address" value="<%= request.getParameter("address") != null ? request.getParameter("address") : "" %>">
                </div>

                <div class="form-group">
                    <label for="phone_number">Điện thoại</label>
                    <input type="text" id="phone_number" name="phone_number" value="<%= request.getParameter("phone_number") != null ? request.getParameter("phone_number") : "" %>">
                </div>

                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input type="text" id="email" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
                </div>

                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" name="username" value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password">
                </div>

                <div class="form-actions">
                    <input type="submit" name="submit" value="Đăng ký">
                    <input type="reset" value="Xóa">
                </div>
            </form>

            <div class="login-link">
                <p>Nếu bạn đã có tài khoản, <a href="LoginURL?service=loginCustomer">đăng nhập tại đây</a>.</p>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>