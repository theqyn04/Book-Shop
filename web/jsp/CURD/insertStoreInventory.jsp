
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <form action="StoreInventoryURL" method="post">
            <input type="hidden" name="service" value="insertStoreInventory">
            <table>
                <tr>
                    <td>Mã cửa hàng</td>
                    <td><input type="text" name="store_id" id=""></td>
                </tr>
                <tr>
                    <td>Mã loại sách</td>
                    <td><input type="text" name="book_id" id=""></td>
                </tr>
                <tr>
                    <td>Số lượng tồn trong kho</td>
                    <td><input type="text" name="stock_quantity" id=""></td>
                </tr>
                
                <tr>
                    <td><input type="submit" name="submit" 
                               value="insertStoreInventory"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
