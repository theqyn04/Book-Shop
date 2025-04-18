<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f4f4f4;
            }
            .container {
                margin-top: 20px;
            }
            .form-group label {
                font-weight: bold;
            }
            .btn-custom {
                background-color: #5cb85c;
                color: white;
            }
            .btn-custom:hover {
                background-color: #AC062E;
            }
            .links a {
                margin-right: 15px;
                color: #AC062E;
                text-decoration: none;
            }
            .links a:hover {
                text-decoration: underline;
            }

            /* Style chung cho các cột */
            .col-md-4 {
                border: 1px solid #ddd; /* Viền màu xám nhạt */
                border-radius: 8px; /* Bo góc viền */
                padding: 20px; /* Khoảng cách bên trong */
                margin: 10px 0; /* Khoảng cách bên ngoài */
                background-color: #fff; /* Nền trắng */
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
            }

            /* Phần tiêu đề của các cột */
            .col-md-4 h2 {
                margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
                color: #333; /* Màu chữ tiêu đề */
            }

            /* Phần nội dung trong các cột */
            .col-md-4 p {
                color: #555; /* Màu chữ nội dung */
            }

            /* Phần form */
            .col-md-4 form {
                margin-top: 20px; /* Khoảng cách trên form */
            }

            /* Phần liên kết */
            .links ul {
                list-style-type: none; /* Bỏ dấu chấm đầu dòng */
                padding-left: 0; /* Xóa padding mặc định */
            }

            .links ul li {
                margin-bottom: 10px; /* Khoảng cách giữa các liên kết */
            }

            .links ul li a {
                color: #337ab7; /* Màu liên kết */
                text-decoration: none; /* Bỏ gạch chân */
            }

            .links ul li a:hover {
                text-decoration: underline; /* Gạch chân khi hover */
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2>KHÁCH HÀNG MỚI</h2>
                    <p><b>Đăng ký tài khoản</b><br>
                        Bằng cách tạo tài khoản, bạn sẽ có thể mua sắm nhanh hơn, cập nhật trạng thái đơn hàng và 
                        theo dõi các đơn hàng bạn đã thực hiện trước đó.</p>
                    <br>
                    <a href="LoginURL?service=registerCustomer" class="btn btn-custom">Đăng ký</a>
                </div>
                <div class="col-md-4">
                    <h2>Phản hồi khách hàng</h2>
                    <p><b>Tôi là một khách hàng cũ</b></p>
                    <form action="LoginURL" method="post">
                        <input type="hidden" name="service" value="loginCustomer">
                        <div class="form-group">
                            <label for="username">Tài khoản</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="userType">Loại tài khoản</label>
                            <select class="form-control" id="userType" name="userType">
                                <option value="customer">Khách hàng</option>
                                <option value="employee">Nhân viên</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" value="Đăng nhập" class="btn btn-custom">
                            <input type="reset" value="Xóa" class="btn btn-secondary">
                        </div>
                    </form>
                </div>
                <div class="col-md-4">
                    <div class="links text-center">
                        <ul>
                            <li><a href="LoginURL?service=loginCustomer">Đăng nhập</a></li>
                            <li><a href="LoginURL?service=registerCustomer">Đăng ký</a></li>
                            <li><a href="#">Quên mật khẩu</a></li>
                            <li><a href="#">Tài khoản của tôi</a></li>
                            <li><a href="#">Lịch sử đơn hàng</a></li>
                        </ul>
                    </div>   
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>