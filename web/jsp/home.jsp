<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.books,java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/homeStyle.css" rel="stylesheet">
    </head>
    <body>

        <%@include file="header.jsp" %>


        <!-- Slider -->
        <div class="slider">
            <div class="slide active">
                <img src="img/banner1.png" alt="Banners">
            </div>
            <div class="slide">
                <img src="img/banner2.png" alt="Banners">
            </div>
            <div class="slide">
                <img src="img/banner3.png" alt="Banners">
            </div>
            <!-- Nút điều khiển -->
            <button class="prev" onclick="prevSlide()">&#10094;</button> <!-- Nút Previous -->
            <button class="next" onclick="nextSlide()">&#10095;</button> <!-- Nút Next -->
        </div>

        <script>
            let currentSlide = 0;
            const slides = document.querySelectorAll('.slide');

            function showSlide(index) {
                // Đảm bảo index nằm trong phạm vi hợp lệ
                if (index >= slides.length) {
                    currentSlide = 0;
                } else if (index < 0) {
                    currentSlide = slides.length - 1;
                } else {
                    currentSlide = index;
                }

                // Ẩn tất cả các slide
                slides.forEach((slide, i) => {
                    slide.classList.remove('active');
                    if (i === currentSlide) {
                        slide.classList.add('active');
                    }
                });
            }

            function nextSlide() {
                showSlide(currentSlide + 1);
            }

            function prevSlide() {
                showSlide(currentSlide - 1);
            }

// Tự động chuyển slide mỗi 5 giây
            setInterval(nextSlide, 5000);

// Hiển thị slide đầu tiên khi trang được tải
            showSlide(currentSlide);
        </script>

        <% // get data from servlet
            List<books> listBestOrder = (List<books>)request.getAttribute("bestOrder");
        %>
        <br>
        <h2>ĐẶT NHIỀU NHẤT</h2>
        <div class="row">
            <% for (books books : listBestOrder) { %>
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

        <% // get data from servlet
            List<books> newBook = (List<books>)request.getAttribute("newBook");
        %>
        <br><h2>SẢN PHẨM MỚI</h2>
        <div class="row">
            <% for (books books : newBook) { %>
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

        <% // get data from servlet
            List<books> listLiteraty = (List<books>)request.getAttribute("listLiteraty");
        %>
        <br><h2>HOA SINH NHẬT</h2>
        <div class="row">
            <% for (books books : listLiteraty) { %>
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

        <style>
            .more-content {
                display: none;
            }

            .more-content.show {
                display: block;
            }
        </style>

        <br>
        <h1>Sách Hà Nội - Giao Hoa Tận Nơi 60 Phút</h1>
        <p>Bạn đang cần đặt sách giao tận nơi tại Hà Nội? Bạn đã tham khảo nhiều dịch vụ mua sách online tại Hà Nội nhưng vẫn 
            chưa tìm được một cửa hàng sách uy tín và đáng tin cậy?<br>
            Nếu bạn vẫn đang tìm một cửa hàng sách Hà Nội cung cấp dịch vụ đặt sách online giao tận nơi, 
            thì QynBooks.vn là sự lựa chọn đáng tin cậy dành cho bạn. Với gần 10 năm hoạt động trong lĩnh vực 
            sách và dịch vụ giao sách tận nơi, QynBooks.vn đã giúp hàng ngàn khách hàng tìm được những cuốn sách 
            ý nghĩa để gửi tặng người thân, bạn bè hoặc phục vụ nhu cầu học tập, giải trí của chính mình.</p>
        <h1>Các dịch vụ của QynFlower.vn</h1>
        <p>QynFlower.vn hiện đang cung cấp hơn 500+ mẫu sách phù hợp để làm quà tặng cho mọi dịp đặc biệt trong năm:</p>
        <ul>
            <li>Sách kinh tế</li>
            <li>Sách kĩ năng sống</li>
            <li>Sách thiếu nhi</li>
            <li>Sách văn học</li>
            <li>Sách ngoại ngữ</li>
            <li>Sách ngoại văn</li>
            <li>Sách chính trị</li>
            <li>Sách văn hóa</li>
        </ul>
        <p>Và tất cả những đầu sách khác.</p>

        <a href="#" id="read-more">Xem thêm...</a>
        <div class="more-content" id="more-content">
            <h1>Tại sao nên chọn QynBook.vn</h1><br>
            <div class="shop-hoa-tuoi-HN">
                <img src="#" alt="shop hoa tuoi HN">
            </div>

            <p>Có hàng trăm <i>shop bán sách ở Hà Nội</i> cung cấp dịch vụ sách giao tận nơi, vậy tại sao bạn nên đặt hoa 
                tại QynFlower.vn?<br>

                Đặt và nhận sách trong 60 phút: Với QynBooks.vn, bạn có thể dễ dàng đặt một cuốn sách yêu thích 
                và nhận ngay trong vòng 60 phút. Dịch vụ giao sách nhanh tận nơi giúp bạn kịp thời gửi tặng sách 
                cho người thân, bạn bè hoặc phục vụ nhu cầu cá nhân một cách nhanh chóng và tiện lợi.<br>

                Sách chất lượng nhất: Sách tại QynBooks.vn đều được tuyển chọn kỹ càng từ các nhà xuất bản uy tín. 
                Tất cả sách đều được kiểm tra chất lượng trước khi giao đến tay khách hàng, đảm bảo sách mới, 
                nguyên vẹn và không lỗi in ấn.<br>

                Sách giống như mô tả: Một trong những nỗi lo của khách hàng khi đặt sách online là sản phẩm thực 
                tế không giống với mô tả trên website. Tuy nhiên, với QynBooks.vn, bạn hoàn toàn có thể yên tâm. 
                Chúng tôi cam kết sách ngoài thực tế giống từ 80-90% so với hình ảnh và mô tả trên website. Nếu 
                có bất kỳ thay đổi nào, chúng tôi sẽ thông báo và xác nhận với khách hàng trước khi giao hàng.<br>

                Đặt sách 24/7: Với QynBooks.vn, dù đang ở đâu, bạn có thể dễ dàng đặt và nhận sách tại Hà Nội 
                vào bất cứ lúc nào. Đội ngũ tư vấn viên online 24/7 sẵn sàng hỗ trợ, giải đáp mọi thắc mắc và 
                giúp bạn lựa chọn sách phù hợp nhất.</p><br>


            <h1>Ưu đãi khi đặt hoa tại QynFlower</h1><br>
            <p>Đặc biệt, khi đặt hoa online tại shop sách Hà Nội QynFlower.vn, khách hàng còn nhận được rất nhiều ưu đãi:</p><br>
            <ul>
                <li>Giảm đến 50k cho khách hàng mới.</li>
                <li>Giảm thêm đến 10% cho khách hàng thân thiết.</li>
                <li>Giao hoa tận nơi Miễn Phí tại nội thành Hà Nội.</li>
                <li>Tặng kèm thiệp chúc mừng.</li>
            </ul>
            <br>
            <p>Bạn đang cần đặt hoa tươi giao tận nơi tại Hà Nội? Gọi ngay 1900 633 045 hoặc chat ngay với 
                QynFlower.vn để được tư vấn lựa chọn một mẫu hoa thật xinh.</p><br>
            <h1>Shop hoa tươi Hà Nội – QynFlower.vn</h1><br>
            <p><b>Địa chỉ:</b> 65 Trần Phú, Phường Điện Bàn, Quận Ba Đình, Hà Nội<br>

                <b>Điện thoại:</b> 1900 633 045 - 0866 590 112<br>

                <b>Website:</b> QynFlower.vn<br>

                <b>Facebook:</b> https://www.facebook.com/qynflower.vn/<br>

                <b>Instagram:</b> https://www.instagram.com/qynflower.vn/<br>

                <b>Zalo:</b> https://oa.zalo.me/1057696361500855184</p><br>
        </div>

        <script>
            document.getElementById('read-more').addEventListener('click', function (event) {
                event.preventDefault();
                var moreContent = document.getElementById('more-content');
                if (moreContent.classList.contains('show')) {
                    moreContent.classList.remove('show');
                    this.textContent = 'Xem thêm...';
                } else {
                    moreContent.classList.add('show');
                    this.textContent = 'Ẩn bớt';
                }
            });
        </script>

        <%@include file="footer.jsp" %>
    </body>
</html>