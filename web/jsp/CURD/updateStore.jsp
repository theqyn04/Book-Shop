
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.stores,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<stores> list=
                    (List<stores>)request.getAttribute("list");
        stores store = list.get(0);
        %>
        <form action="StoreURL" method="post">
            <input type="hidden" name="service" value="updateStore">
            <table>
                <tr>
                    <td>Mã cửa hàng</td>
                    <td><input type="text" name="store_id" id="" value=<%=store.getStore_id()%>></td>
                </tr>
                <tr>
                    <td>Tên cửa hàng</td>
                    <td><input type="text" name="store_name" id="" value=<%=store.getStore_name()%>></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="address" id="" value=<%=store.getAddress()%>></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="phone_number" id="" value="<%=store.getPhone_number()%>"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" id=""value=<%=store.getEmail()%>></td>
                </tr>
                
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateStore"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
