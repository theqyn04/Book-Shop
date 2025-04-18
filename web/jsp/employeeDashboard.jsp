
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.OrderDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .sidebar {
                height: 100vh;
                background-color: #AC062E;
                color: white;
            }
            .sidebar a {
                color: white;
            }
            .sidebar a:hover {
                color: #f8f9fa;
                text-decoration: none;
            }
            .content {
                padding: 20px;
            }
            body {
                background-image: url('img/banner1.png'); /* Đường dẫn đến hình ảnh */
                background-size: cover; /* Đảm bảo hình ảnh phủ kín toàn bộ nền */
                background-position: center; /* Căn giữa hình ảnh */
                background-attachment: fixed; /* Giữ hình ảnh cố định khi cuộn trang */
                position: relative;
            }

            /* Tạo lớp phủ mờ */
            body::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(255, 255, 255, 0.5); /* Màu nền trắng với độ trong suốt */
                backdrop-filter: blur(5px); /* Hiệu ứng mờ */
                z-index: -1; /* Đặt lớp phủ phía sau nội dung */
            }

            /* Đảm bảo nội dung hiển thị trên lớp phủ */
            .container-fluid {
                position: relative;
                z-index: 1;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-2 sidebar">
                    <h3 class="text-center mt-3">Nhân viên</h3>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="OrderURL?service=listOrder">Quản lý đơn hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="BookURL?service=listBook">Quản lý sản phẩm</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CustomerURL?service=listCustomer">Quản lý khách hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CURDCategoryURL?service=listBookType">Quản lý loại sản phẩm</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="StoreURL?service=listStore">Quản lý của hàng</a>
                        </li>
                        
                        <li class="nav-item">
                            <a class="nav-link" href="LogoutURL">Đăng xuất</a>
                        </li>
                    </ul>
                </div>

                <!-- Main content -->
                <div class="col-md-10 content">
                    <h1>Chào mừng, ${userName}!</h1>
                    <p>Bạn đang đăng nhập với vai trò nhân viên.</p>

                    <!-- Thống kê nhanh -->
                    <%
    OrderDAO orderDAO = new OrderDAO();
    int totalOrders = orderDAO.getTotalOrders();
    int newOrders = orderDAO.getNewOrdersLast7Days();
    double totalRevenue = orderDAO.getRevenueLast7Days();
                    %>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Tổng đơn hàng</h5>
                                    <p class="card-text"><%= totalOrders %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Đơn hàng mới</h5>
                                    <p class="card-text"><%= newOrders %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Doanh thu</h5>
                                    <p class="card-text"><%= String.format("%,.0f", totalRevenue) %> VNĐ</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
