
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="insertCustomer">
            <table>
                <tr>
                    <td>Họ và tên</td>
                    <td><input type="text" name="full_name" id=""></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="address" id=""></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="phone_number" id=""></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" id=""></td>
                </tr>
                <tr>
                    <td>Tài khoản</td>
                    <td><input type="text" name="username" id=""></td>
                </tr>
                <tr>
                    <td>Mật khẩu</td>
                    <td><input type="text" name="password" id=""></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="insertCustomer"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
