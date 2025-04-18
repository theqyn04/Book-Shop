
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.orders,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        <% // get data from servlet
            List<orders> list = (List<orders>) request.getAttribute("orderData");
            String titleTable=(String)request.getAttribute("titleTable");
        %>

        <form action="OrderURL" method="get">
            <input type="hidden" name="service" value="listOrder">
            <p>Tìm kiếm theo mã khách hàng
                <input type="text" name="cid"><!-- comment -->
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="OrderURL?service=insertOrder">Insert Order</a> 
        <table border=1>
            <caption><%=titleTable%></caption>
            <tr>
                <th>Mã đơn hàng</th>
                <th>Mã khách hàng</th>
                <th>Ngày đặt hàng</th>
                <th>Tổng giá tiền</th>
                <th>Trạng thái</th>
                <th>Chỉnh sửa</th>
                <th>Xóa</th>
            </tr>
            <%for (orders order : list) {%>
            <tr>
                <td><%=order.getOrder_id()%></td>
                <td><%=order.getCustomer_id()%></td>
                <td><%=order.getOrder_date()%></td>
                <td><%=order.getTotal_amount()%></td>
                <td><%=order.getStatus()%></td>
                <td><a href="OrderURL?service=updateOrder&oid=<%=order.getOrder_id()%>">Chỉnh sửa</a></td>
                <td><a href="OrderURL?service=deleteOrder&oid=<%=order.getOrder_id()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
