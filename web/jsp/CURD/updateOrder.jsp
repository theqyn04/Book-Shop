
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.orders,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<orders> list=
                    (List<orders>)request.getAttribute("list");
        orders order = list.get(0);
        %>
        <form action="OrderURL" method="post">
            <input type="hidden" name="service" value="updateOrder">
            <table>
                <tr>
                    <td>Mã đơn hàng</td>
                    <td><input type="text" name="order_id" id="" value="<%=order.getOrder_id()%>"></td>
                </tr>
                <tr>
                    <td>Mã khách hàng</td>
                    <td><input type="text" name="customer_id" value="<%=order.getCustomer_id()%>"></td>
                </tr>
                <tr>
                    <td>Ngày đặt hàng</td>
                    <td><input type="datetime-local" name="order_date" id="" value="<%=order.getOrder_date()%>"></td>
                </tr>
                <tr>
                    <td>Tổng đơn hàng</td>
                    <td><input type="text" name="total_amount" id="" value="<%=order.getTotal_amount()%>"></td>
                </tr>>
                <tr>
                    <td>Trạng thái</td>
                    <td><input type="text" id="" name="status" value="<%=order.getStatus()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateOrder"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
