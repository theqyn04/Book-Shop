
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.stores,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        <% // get data from servlet
            List<stores> list = (List<stores>) request.getAttribute("storeData");
            String titleTable=(String)request.getAttribute("titleTable");
        %>
        <form action="StoreURL" method="get">
            <input type="hidden" name="service" value="listStore">
            <p>Tìm kiếm theo mã cửa hàng
                <input type="text" name="sid">
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="StoreURL?service=insertStore">Thêm cửa hàng mới</a> 
        <table border=1>
            <caption><%=titleTable%></caption>
            <tr>
                <th>Mã cửa hàng</th>
                <th>Tên cửa hàng</th>
                <th>Địa chỉ</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Chỉnh sửa</th>
                <th>Xóa</th>
            </tr>
            <%for (stores store : list) {%>
            <tr>
                <td><%=store.getStore_id()%></td>
                <td><%=store.getStore_name()%></td>
                <td><%=store.getAddress()%></td>
                <td><%=store.getPhone_number()%></td>
                <td><%=store.getEmail()%></td>
                <td><a href="StoreURL?service=updateStore&sid=<%=store.getStore_id()%>">Chỉnh sửa</a></td>
                <td><a href="StoreURL?service=deleteStore&sid=<%=store.getStore_id()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
