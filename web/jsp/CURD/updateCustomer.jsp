
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customers,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<customers> list=
                    (List<customers>)request.getAttribute("list");
        customers customer = list.get(0);
        %>
        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="updateCustomer">
            <table>
                <tr>
                    <td>Mã khách hàng</td>
                    <td><input type="text" name="customer_id" id="" value=<%=customer.getCustomer_id()%>></td>
                </tr>
                <tr>
                    <td>Họ và tên</td>
                    <td><input type="text" name="full_name" id="" value=<%=customer.getFull_name()%>></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="address" id="" value=<%=customer.getAddress()%>></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="phone_number" id="" value="<%=customer.getPhone_number()%>"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" id=""value=<%=customer.getEmail()%>></td>
                </tr>
                <tr>
                    <td>Tài khoản</td>
                    <td><input type="text" name="username" id="" value=<%=customer.getUsername()%>></td>
                </tr>
                <tr>
                    <td>Mật khẩu</td>
                    <td><input type="text" name="password" id="" value=<%=customer.getPassword()%>></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateCustomer"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
