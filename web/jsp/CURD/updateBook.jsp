
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.books,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<books> list=
                    (List<books>)request.getAttribute("list");
        books book = list.get(0);
        %>
        <form action="BookURL" method="post">
            <input type="hidden" name="service" value="updateBook">
            <table>
                <tr>
                    <td>Mã sản phẩm</td>
                    <td><input type="text" name="book_id" id="" value="book.getBook_id()"></td>
                </tr>
                <tr>
                    <td>Tên sản phẩm</td>
                    <td><input type="text" name="book_name" id="" value="book.getBook_name()"></td>
                </tr>
                <tr>
                    <td>Mã loại sản phẩm</td>
                    <td><input type="text" name="book_type_id" id="" value="book.getBook_type_id()"></td>
                </tr>
                <tr>
                    <td>Giá</td>
                    <td><input type="text" name="price" id="" value="book.getPrice()"></td>
                </tr>
                <tr>
                    <td>Số lượng tồn trong kho</td>
                    <td><input type="text" name="stock_quantity" id="" value="book.getStock_quantity()"></td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td><input type="text" name="description" id=""value="book.getDescription()"></td>
                </tr>
                <tr>
                    <td>Đường link ảnh</td>
                    <td><input type="text" name="imageURL" id=""value="book.getImageURL()"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateBook"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
