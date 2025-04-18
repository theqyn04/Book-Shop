
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <form action="StoreURL" method="post">
            <input type="hidden" name="service" value="insertStore">
            <table>
                <tr>
                    <td>Tên cửa hàng</td>
                    <td><input type="text" name="store_name" id=""></td>
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
                    <td><input type="submit" name="submit" 
                               value="insertStore"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
