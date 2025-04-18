
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.store_inventory,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleForm.css" rel="stylesheet">
    </head>
    <body>
        <%List<store_inventory> list=
                    (List<store_inventory>)request.getAttribute("list");
        store_inventory store = list.get(0);
        %>
        <form action="StoreInventoryURL" method="post">
            <input type="hidden" name="service" value="updateStoreInventory">
            <table>
                <tr>
                    <td>Mã kho</td>
                    <td><input type="text" name="id" id="" value=<%=store.getId()%>></td>
                </tr>
                <tr>
                    <td>Mã cửa hàng</td>
                    <td><input type="text" name="store_id" id="" value=<%=store.getStore_id()%>></td>
                </tr>
                <tr>
                    <td>Mã sản phẩm</td>
                    <td><input type="text" name="book_id" id="" value=<%=store.getBook_id()%>></td>
                </tr>
                <tr>
                    <td>Sản phẩm tồn kho</td>
                    <td><input type="text" name="stock_quantity" id="" value="<%=store.getStock_quantity()%>"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" name="submit" 
                               value="updateStoreInventory"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
