package dao;

import model.customers;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CustomerDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from customers where customer_id=" + id;
        try {
            String sqlSelect = "select customer_id from orders where customer_id=" + id;
            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(customers cus) {
        int n = 0;
        String sql = """
           UPDATE [customers]
           SET  [full_name] = ?
                 ,[address] = ?
                 ,[phone_number] = ?
                 ,[email] = ?
                 ,[username] = ?
                 ,[password] = ?
           WHERE [customer_id] =?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFull_name());
            pre.setString(2, cus.getAddress());
            pre.setString(3, cus.getPhone_number());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getUsername());
            pre.setString(6, cus.getPassword());
            pre.setInt(7, cus.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(customers cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([full_name]\n"
                + "           ,[address]\n"
                + "           ,[phone_number]\n"
                + "           ,[email]\n"
                + "           ,[username]\n"
                + "           ,[password])\n"
                + "     VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFull_name());
            pre.setString(2, cus.getAddress());
            pre.setString(3, cus.getPhone_number());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getUsername());
            pre.setString(6, cus.getPassword());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<customers> getCustomers(String sql) {
        List<customers> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int customer_id = rs.getInt("customer_id");
                String full_name = rs.getString("full_name");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                customers cus = new customers(customer_id, full_name, address, phone_number, email, username, password);
                list.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public customers loginCustomer(String user, String pass) {
        String sql = "select * from customers where username=? and password=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new customers(rs.getInt("customer_id"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public customers getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return new customers(
                        rs.getInt("customer_id"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) AS count FROM customers WHERE username = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
