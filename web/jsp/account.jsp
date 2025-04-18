<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin tài khoản</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="css/headerStyle.css" rel="stylesheet">
        <style>
            .account-info {
                max-width: 1200px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 10px;
                background-color: #f9f9f9;
                display: flex;
                justify-content: space-between;
            }
            .account-info .personal-info, .account-info .order-history {
                width: 48%;
            }
            .account-info h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .account-info p {
                font-size: 18px;
                margin: 10px 0;
            }
            .order-history table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            .order-history th, .order-history td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            .order-history th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <%@include file="header.jsp" %>

        <div class="container">
            <div class="account-info">
                <div class="personal-info">
                    <c:if test="${not empty customer}">
                        <h1>Thông tin cá nhân</h1>
                        <p>ID: ${customer.customer_id}</p>
                        <p>Tên: ${customer.full_name}</p>
                        <p>Địa chỉ: ${customer.address}</p>
                        <p>Số điện thoại: ${customer.phone_number}</p>
                        <p>Email: ${customer.email}</p>
                        <p>Username: ${customer.username}</p>
                    </c:if>
                    <c:if test="${empty customer}">
                        <h1>Không tìm thấy thông tin khách hàng</h1>
                    </c:if>
                </div>
                <div class="order-history">
                    <h1>Lịch sử đơn hàng</h1>
                    <c:if test="${not empty historyOrd}">
                        <table>
                            <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Ngày đặt hàng</th>
                                    <th>Tổng tiền</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="historyOrd" items="${historyOrd}">
                                    <tr>
                                        <td>${historyOrd.order_id}</td>
                                        <td>${historyOrd.order_date}</td>
                                        <td>${historyOrd.total_amount}</td>
                                        <td>${historyOrd.status}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty historyOrd}">
                        <p>Không có đơn hàng nào.</p>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>