package dao;

import model.orders;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Timestamp;

public class OrderDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from orders where order_id=" + id;
        try {
            String sqlSelect = "select order_id from order_details where order_id=" + id;

            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(orders ord) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[orders]
                SET [customer_id] = ?
                   ,[order_date] = ?
                   ,[total_amount] = ?
                   ,[status] = ?
              WHERE order_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getCustomer_id());
            pre.setTimestamp(2, ord.getOrder_date());
            pre.setDouble(3, ord.getTotal_amount());
            pre.setString(4, ord.getStatus());
            pre.setInt(5, ord.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(orders ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([customer_id]\n"
                + "           ,[order_date]\n"
                + "           ,[total_amount]\n"
                + "           ,[status])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getCustomer_id());
            pre.setTimestamp(2, ord.getOrder_date()); // Sử dụng setTimestamp thay vì setString
            pre.setDouble(3, ord.getTotal_amount());
            pre.setString(4, ord.getStatus());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<orders> getOrder(String sql) {
        List<orders> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int order_id = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                Timestamp order_date = rs.getTimestamp("order_date");
                double total_amount = rs.getDouble("total_amount");
                String status = rs.getString("status");
                orders ord = new orders(order_id, customer_id, order_date, total_amount, status);
                list.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getTotalOrders() {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) AS total_orders FROM orders";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                totalOrders = rs.getInt("total_orders");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalOrders;
    }

    public double getRevenueLast7Days() {
        double totalRevenue = 0;
        String sql = "SELECT SUM(total_amount) AS total_revenue FROM orders WHERE order_date >= DATEADD(DAY, -7, GETDATE())";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                totalRevenue = rs.getDouble("total_revenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRevenue;
    }

    public int getNewOrdersLast7Days() {
        int newOrders = 0;
        String sql = "SELECT COUNT(*) AS new_orders FROM orders WHERE status = 'new' OR order_date >= DATEADD(DAY, -7, GETDATE())";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                newOrders = rs.getInt("new_orders");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newOrders;
    }

}
