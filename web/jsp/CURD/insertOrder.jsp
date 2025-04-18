
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <form action="OrderURL" method="post">
            <input type="hidden" name="service" value="insertOrder">
            <table>
                <tr>
                    <td>Mã khách hàng</td>
                    <td><input type="text" name="customer_id" id=""></td>
                </tr>
                <tr>
                    <td>Ngày đặt hàng</td>
                    <td><input type="datetime-local" name="order_date" id=""></td>
                </tr>
                <tr>
                    <td>Tổng đơn hàng</td>
                    <td><input type="text" name="total_amount" id=""></td>
                </tr>
                <tr>
                    Trạng thái đơn hàng<input type="radio" name="status" value="processing"/>Đang xử lí
                    <input type="radio" name="status" value="shipping"/>Đang giao hàng
                    <input type="radio" name="status" value="done"/>Đã giao thành công
                    <input type="radio" name="status" value="canceled"/>Đã hủy 
   
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="insertOrder"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
