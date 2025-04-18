
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.store_inventory,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        
        <% // get data from servlet
            List<store_inventory> list =
                    (List<store_inventory>)request.getAttribute("storeInventoryData");
            
        %>
        <form action="StoreInventoryURL" method="get">
            <input type="hidden" name="service" value="listStoreInventory">
            <p>Tìm kiếm theo mã kho
                <input type="text" name="sid"><!-- comment -->
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="StoreInventoryURL?service=insertStoreInventory">Thêm mới kho</a> 
        <table border=1>
            <tr>
                <th>Mã kho</th>
                <th>Mã cửa hàng</th>
                <th>Mã sản phẩm</th>
                <th>Sản phẩm tồn kho</th>
                <th>Sửa thông tin</th>
                <th>Xóa</th>
            </tr>
            <%for (store_inventory flower : list) {%>
            <tr>
                <td><%=flower.getId()%></td>
                <td><%=flower.getStore_id()%></td>
                <td><%=flower.getBook_id()%></td>
                <td><%=flower.getStock_quantity()%></td>
                
                <td><a href="StoreInventoryURL?service=updateStoreInventory&sid=<%=flower.getId()%>">Sửa thông tin</a></td>
                <td><a href="StoreInventoryURL?service=deleteStoreInventory&sid=<%=flower.getId()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
