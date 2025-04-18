<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, model.cart" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
        <link rel="stylesheet" href="css/cartStyle.css"> <!-- Liên kết file CSS -->
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="cart-container">
            <%
            String message = request.getParameter("message");
            if (message != null) {
            %>
            <p class="error-message">Hãy đăng nhập để mua hàng</p>
            <a href="LoginURL?service=loginCustomer">Đăng nhập ngay</a>
            <%
            }

            List<cart> list = (List<cart>) request.getAttribute("listCart");
            Double totalAmount = (Double) request.getAttribute("totalAmount"); // Sử dụng Double thay vì double
            if (list != null && !list.isEmpty()) {
            %>
            <caption>Giỏ hàng của bạn</caption>
            <table>
                <tr>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
                <% for (cart book : list) { %>
                <tr>
                    <td><img src="<%=book.getImageURL()%>" alt="<%=book.getBook_name()%>"></td>
                    <td><%=book.getBook_name()%></td>
                    <td><%=book.getPrice()%> VND</td>
                    <td><%=book.getQuantity()%></td>
                    <td><%=book.getQuantity() * book.getPrice()%> VND</td>
                    <td><a href="CartURL?service=removeBook&bid=<%=book.getBook_id()%>" class="btn btn-danger">Xóa</a></td>
                </tr>
                <% } %>
            </table>
            <%
                if (totalAmount != null) {
            %>
            <p class="total-amount">Tổng số tiền cần thanh toán: <%=totalAmount%> VND</p>
            <%
                } else {
            %>
            <p class="total-amount">Tổng số tiền không khả dụng.</p>
            <%
                }
            } else {
            %>
            <p class="empty-cart">Giỏ hàng của bạn đang trống.</p>
            <% } %>

            <!-- Button container -->
            <div class="button-container">
                <!-- Nút Mua hàng -->
                <form action="CartURL" method="post">
                    <input type="hidden" name="service" value="checkout">
                    <input type="submit" value="Mua hàng" class="btn btn-primary">
                </form>

                <!-- Nút Xóa tất cả -->
                <form action="CartURL" method="post">
                    <input type="hidden" name="service" value="removeAll">
                    <input type="submit" value="Xóa tất cả" class="btn btn-danger">
                </form>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>