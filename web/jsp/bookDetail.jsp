<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.books,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bookDetailStyle.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <%
            // Lấy thông tin sản phẩm từ request
            books book = (books) request.getAttribute("book");
            if (book != null) {
        %>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6">
                    <img src="<%=book.getImageURL()%>" alt="<%=book.getBook_name()%>" class="img-fluid">
                </div>
                <div class="col-md-6">
                    <h1><%=book.getBook_name()%></h1>
                    <p class="price"><strong>Giá: <%=book.getPrice()%> VND</strong></p>
                    <p><%=book.getDescription()%></p>
                    

                    <!-- Phần khuyến mãi -->
                    <div class="promotion-info">
                        <h2>Khuyến mãi</h2>
                        <ul>
                            <li>Giảm 50K</li>
                            <li>Giảm 25K</li>
                            <li>Giảm 10%</li>
                        </ul>
                    </div>

                    <!-- Phần vận chuyển -->
                    <div class="shipping-info">
                        <h3>Vận chuyển</h3>
                        <p>Miễn phí giao hoa khu vực nội thành TP.HCM & Hà Nội</p>
                        <p>Phí giao hàng: Miễn phí</p>
                        <p>Sản phẩm này không hỗ trợ giao vào ngày: 08-03-2025</p>
                    </div>
                    <br><br>
                    
                    <!-- Thêm vào giỏ hàng -->
                    <a href="CartURL?service=add2cart&bid=<%=book.getBook_id()%>" class="btn btn-primary">Thêm vào giỏ hàng</a>
                </div>
            </div>
        </div>
        <% } else { %>
        <div class="container mt-5">
            <p>Sản phẩm không tồn tại.</p>
        </div>
        <% } %>
        
        <h2>Lưu ý:</h2>
        <p><i>**Vì một số đầu sách có thể thay đổi tùy theo tình trạng kho hàng, QynBooks đảm bảo các tựa sách chính sẽ được 
                giao đúng theo yêu cầu.<br>

            **Các sản phẩm đi kèm (nếu có) có thể thay đổi linh hoạt để phù hợp với giá cả và chất lượng dịch vụ.</i></p>
            
            <h2>Sản phẩm liên quan</h2><br>
            <br>
        
        <%
            List <books> listRelevant = (List<books>) request.getAttribute("listRelevant");
        %>
        <div class="row">
            <% for (books books : listRelevant) { %>
            <div class="col-md-3 col-sm-6 mb-4"> <!-- Thêm lớp mb-4 để tạo khoảng cách giữa các hàng -->
                <div class="thumbnail h-100 d-flex flex-column"> <!-- Thêm lớp h-100 và d-flex flex-column -->
                    <a href="BookDetailURL?bid=<%=books.getBook_id()%>">
                        <img src="<%=books.getImageURL()%>" alt="<%=books.getBook_name()%>" class="img-fluid">
                    </a>
                    <div class="caption d-flex flex-column flex-grow-1"> <!-- Thêm lớp d-flex flex-column flex-grow-1 -->
                        <h3><%=books.getBook_name()%></h3>
                        <p class="price"><%=books.getPrice()%>VND</p>
                        <a href="CartURL?service=add2cart&bid=<%=books.getBook_id()%>" class="btn btn-primary btn-block mt-auto">ĐẶT HÀNG</a>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <br><br>
            
        <%@include file="footer.jsp" %>
    </body>
</html>