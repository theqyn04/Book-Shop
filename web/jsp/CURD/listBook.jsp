
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.books,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        <% // get data from servlet
            List<books> list = (List<books>) request.getAttribute("bookData");
            String titleTable=(String)request.getAttribute("titleTable");
        %>
        <form action="BookURL" method="get">
            <input type="hidden" name="service" value="listBook">
            <p>Tìm kiếm theo mã sản phẩm
                <input type="text" name="bid"><!-- comment -->
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="BookURL?service=insertBook">Thêm sản phẩm</a> 
        <table border=1>
            <caption><%=titleTable%></caption>
            <tr>
                <th>Mã sản phẩm</th>
                <th>Tên sản phẩm</th>
                <th>Mã loại sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng hàng tồn kho</th>
                <th>Mô tả</th>
                <th>Ảnh</th>
                <th>Chỉnh sửa</th>
                <th>Xóa</th>
            </tr>
            <%for (books books : list) {%>
            <tr>
                <td><%=books.getBook_id()%></td>
                <td><%=books.getBook_name()%></td>
                <td><%=books.getBook_type_id()%></td>
                <td><%=books.getPrice()%></td>
                <td><%=books.getStock_quantity()%></td>
                <td><%=books.getDescription()%></td>
                <td> <img src="<%=books.getImageURL()%>" alt="bookURL"></td>
                <td><a href="BookURL?service=updateBook&bid=<%=books.getBook_id()%>">Chỉnh sửa</a></td>
                <td><a href="BookURL?service=deleteBook&bid=<%=books.getBook_id()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
