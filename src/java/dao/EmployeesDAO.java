
package dao;

import model.employees;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EmployeesDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from employyes where employee_id=" + id;
        try {

            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(employees emp) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[employees]
              SET [full_name] = ?
                 ,[phone_number] = ?
                 ,[email] = ?
                 ,[address] = ?
                 ,[store_id] = ?
                 ,[username] = ?
                 ,[password] = ?
            WHERE employee_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, emp.getFull_name());
            pre.setString(2, emp.getPhone_number());
            pre.setString(3, emp.getEmail());
            pre.setString(4, emp.getAddress());
            pre.setInt(5, emp.getStore_id());
            pre.setString(6, emp.getUsername());
            pre.setString(7, emp.getPassword());
            pre.setInt(8, emp.getEmployee_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(employees emp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[employees]\n"
                + "           (employee_id, [full_name]\n"
                + "           ,[phone_number]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[store_id]\n"
                + "           ,[username]\n"
                + "           ,[password])\n"
                + "           VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, emp.getEmployee_id());
            pre.setString(2, emp.getFull_name());
            pre.setString(3, emp.getPhone_number());
            pre.setString(4, emp.getEmail());
            pre.setString(5, emp.getAddress());
            pre.setInt(6, emp.getStore_id());
            pre.setString(7, emp.getUsername());
            pre.setString(8, emp.getPassword());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<employees> getEmployee(String sql) {
        List<employees> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int employee_id = rs.getInt("employee_id");
                String full_name = rs.getString("full_name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int store_id = rs.getInt("store_id");
                
                String username = rs.getString("username");
                String password = rs.getString("password");
                employees emp = new employees(employee_id, full_name, phone_number, email, address, store_id, username, password);
                list.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public employees loginEmployee(String user, String pass) {
        String sql = "select * from employees where username=? and password=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                return new employees(rs.getInt("employee_id"), 
                        rs.getString("full_name"), 
                        rs.getString("phone_number"), 
                        rs.getString("email"), 
                        rs.getString("address"), 
                        rs.getInt("store_id"), 
                        rs.getString("username"), 
                        rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
