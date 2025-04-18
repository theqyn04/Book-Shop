<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="css/headerStyle.css" rel="stylesheet">
    </head>
    <body>
        <!-- Header -->
        <nav class="navbar navbar-expand-lg navbar-light navbar-custom">
            <div class="container-fluid">
                <div class="navbar-brand">
                    HOTLINE: 1900 633 049 | 0866 590 112
                </div>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <!-- Kiểm tra trạng thái đăng nhập -->

                        <c:if test="${empty sessionScope.userName}">
                            <!-- Nếu chưa đăng nhập, hiển thị Đăng nhập và Đăng ký -->
                            <li class="nav-item">
                                <a class="nav-link" href="LoginURL?service=loginCustomer">
                                    <i class="fas fa-user nav-icon"></i> Đăng nhập
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LoginURL?service=registerCustomer">
                                    <i class="fas fa-user-plus nav-icon"></i> Đăng ký
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.userName}">
                            <!-- Nếu đã đăng nhập, hiển thị Tài khoản của tôi và nút Đăng xuất -->
                            <li class="nav-item">
                                <a class="nav-link" href="AccountURL?service=viewAccount&id=${sessionScope.userId}">
                                    <i class="fas fa-user nav-icon"></i> Tài khoản của tôi
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LogoutURL">
                                    <i class="fas fa-sign-out-alt nav-icon"></i> Đăng xuất
                                </a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="CartURL?service=showCart">
                                <i class="fas fa-shopping-cart nav-icon"></i> Giỏ hàng
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CartURL?service=showCart">
                                <i class="fas fa-credit-card nav-icon"></i> Thanh toán
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Thông báo đăng nhập thành công -->
        <c:if test="${not empty sessionScope.loginMessage}">
            <div class="alert alert-success">
                ${sessionScope.loginMessage}
            </div>
            <c:remove var="loginMessage" scope="session" /> <!-- Xóa thông báo sau khi hiển thị -->
        </c:if>

        <!-- Header Content -->
        <div class="header-content">
            <div class="link-social">
                <a href="https://www.facebook.com/nguyen.the.quyen.366928" class="social-icon" target="_blank">
                    <i class="fab fa-facebook"></i>
                </a>
                <!-- Twitter -->
                <a href="https://x.com/Qyn201004" class="social-icon" target="_blank">
                    <i class="fab fa-twitter"></i>
                </a>
                <!-- Instagram -->
                <a href="https://www.instagram.com/theqyn__/" class="social-icon" target="_blank">
                    <i class="fab fa-instagram"></i>
                </a>
            </div>
            <div class="logo">
                <a href="HomeURL?service=home"><img src="img/book_shop_logo.png" alt="Logo"></a>
            </div>
            <div class="search-container">
                <form action="BookURL" method="get">
                    <input type="hidden" name="service" value="listBook">
                    <input class="form-control" type="text" name="bname" placeholder="Tìm kiếm">
                    <button class="btn btn-outline-danger" type="submit">🔍</button>
                </form>
            </div>
        </div>

        <!-- Menu -->
        <div class="menu">
            <a href="CategoryURL?service=listBook&id=1">Văn học</a>
            <a href="CategoryURL?service=listBook&id=2">Kinh tế</a>
            <a href="CategoryURL?service=listBook&id=3">Tâm lý - Kĩ năng sống</a>
            <a href="CategoryURL?service=listBook&id=4">Thiếu nhi</a>
            <a href="CategoryURL?service=listBook&id=5">Sách học ngoại ngữ</a>
            <a href="CategoryURL?service=listAllBook">Tất cả</a>
            <a href="" class="highlight">Sản phẩm giảm 30%</a>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            window.addEventListener('scroll', function () {
                const menu = document.querySelector('.menu');
                const menuOffsetTop = menu.offsetTop;

                if (window.scrollY > menuOffsetTop) {
                    menu.classList.add('fixed');
                } else {
                    menu.classList.remove('fixed');
                }
            });
        </script>
    </body>
</html>