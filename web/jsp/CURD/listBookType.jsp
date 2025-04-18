
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.book_types,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        <% // get data from servlet
            List<book_types> list = (List<book_types>) request.getAttribute("bookTypeData");
            String titleTable=(String)request.getAttribute("titleTable");
        %>
        <form action="CURDCategoryURL" method="get">
            <input type="hidden" name="service" value="listBookType">
            <p>Tìm kiếm theo mã nhóm sản phẩm
                <input type="text" name="bid"><!-- comment -->
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="CURDCategoryURL?service=insertBookType">Thêm loại sản phẩm mới</a> 
        <table border=1>
            <caption><%=titleTable%></caption>
            <tr>
                <th>Mã loại sản phẩm</th>
                <th>Tên loại sản phẩm</th>
                
                <th>Mô tả</th>

                <th>Chỉnh sửa</th>
                <th>Xóa</th>
            </tr>
            <%for (book_types book : list) {%>
            <tr>
                <td><%=book.getBook_type_id()%></td>
                <td><%=book.getbook_type_name()%></td>
                <td><%=book.getDescription()%></td>
                
                <td><a href="CURDCategoryURL?service=updateBookType&bid=<%=book.getBook_type_id()%>">Chỉnh sửa</a></td>
                <td><a href="CURDCategoryURL?service=deleteBookType&bid=<%=book.getBook_type_id()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
