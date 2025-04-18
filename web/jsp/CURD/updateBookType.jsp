
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.book_types,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<book_types> list=
                    (List<book_types>)request.getAttribute("list");
        book_types book = list.get(0);
        %>
        <form action="CURDCategoryURL" method="post">
            <input type="hidden" name="service" value="updateBookType">
            <table>
                <tr>
                    <td>Mã loại sản phẩm</td>
                    <td><input type="text" name="book_type_id" id="" value="<%=book.getBook_type_id()%>"></td>
                </tr>
                <tr>
                    <td>Tên loại sản phẩm</td>
                    <td><input type="text" name="book_type_name" id="" value="<%=book.getBook_type_name()%>"></td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td><input type="text" name="description" id="" value="<%=book.getDescription()%>"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateBookType"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
