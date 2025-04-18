
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <form action="CURDCategoryURL" method="post">
            <input type="hidden" name="service" value="insertBookType">
            <table>
               <tr>
                    <td>Mã loại sách</td>
                    <td><input type="text" name="book_type_id" id=""></td>
                </tr>
                <tr>
                    <td>Tên loại sách</td>
                    <td><input type="text" name="book_type_name" id=""></td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td><input type="text" name="description" id=""></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" 
                               value="insertBookType"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
