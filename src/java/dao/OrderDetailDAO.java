package dao;

import model.order_details;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class OrderDetailDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from order_details where order_detail_id=" + id;
        try {
            String sqlSelect = "select order_detail_id from orders where order_detail_id=" + id;

            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(order_details ord) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[order_details]
               SET [order_id] = ?
                  ,[book_id] = ?
                  ,[quantity] = ?
                  ,[unit_price] = ?
                  ,[subtotal] = ?
             WHERE order_detail_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getOrder_id());
            pre.setInt(2, ord.getBook_id());
            pre.setInt(3, ord.getQuantity());
            pre.setDouble(4, ord.getUnit_price());
            pre.setDouble(5, ord.getSubtotal());
            pre.setInt(6, ord.getOrder_detail_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(order_details ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_details]\n"
                + "           (order_detail_id, [order_id]\n"
                + "           ,[book_id]\n"
                + "           ,[quantity]\n"
                + "           ,[unit_price]\n"
                + "           ,[subtotal])\n"
                + "     VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getOrder_detail_id());
            pre.setInt(2, ord.getOrder_id());
            pre.setInt(3, ord.getBook_id());
            pre.setInt(4, ord.getQuantity());
            pre.setDouble(5, ord.getUnit_price());
            pre.setDouble(6, ord.getSubtotal());
            

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<order_details> getOrderDetail(String sql) {
        List<order_details> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int order_detail_id = rs.getInt("order_detail_id");
                int order_id = rs.getInt("order_id");
                int book_id = rs.getInt("book_id");
                int quantity = rs.getInt("quantity");
                double unit_price = rs.getDouble("unit_price");
                double subtotal = rs.getDouble("subtotal");
                order_details ord = new order_details(order_detail_id, order_id, book_id, quantity, unit_price, subtotal);
                list.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
