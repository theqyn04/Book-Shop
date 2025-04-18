
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customers,java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styleTable.css" rel="stylesheet">
    </head>
    <body>
        
        <% // get data from servlet
            List<customers> listCus =
                    (List<customers>)request.getAttribute("customerData");
            
        %>
        <form action="CustomerURL" method="get">
            <input type="hidden" name="service" value="listCustomer">
            <p>Tìm kiếm
                <input type="text" name="cname"><!-- comment -->
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
         </form>
        
        <p><a href="CustomerURL?service=insertCustomer">Insert Customer</a> 
        <table border=1>
            <tr>
                <th>Id khách hàng</th>
                <th>Họ và tên</th>
                <th>Địa chỉ</th>
                <th>số điện thoại</th>
                <th>Email</th>
                <th>username</th>
                <th>password</th>
                <th>Sửa thông tin</th>
                <th>Xóa</th>
            </tr>
            <%for (customers customer : listCus) {%>
            <tr>
                <td><%=customer.getCustomer_id()%></td>
                <td><%=customer.getFull_name()%></td>
                <td><%=customer.getAddress()%></td>
                <td><%=customer.getPhone_number()%></td>
                <td><%=customer.getEmail()%></td>
                <td><%=customer.getUsername()%></td>
                <td><%=customer.getPassword()%></td>
                <td><a href="CustomerURL?service=updateCustomer&cid=<%=customer.getCustomer_id()%>">Sửa thông tin</a></td>
                <td><a href="CustomerURL?service=deleteCustomer&cid=<%=customer.getCustomer_id()%>">Xóa</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
