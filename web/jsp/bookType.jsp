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

        
        <% // get data from servlet
            List<books> list=
                    (List<books>)request.getAttribute("bookTypeData");            
           
        %>

        <h2></h2>
        <div class="row">
            <% for (books books : list) { %>
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

    </table>

    <%@include file="footer.jsp" %>
</body>
</html>
