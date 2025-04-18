
package dao;

import model.stores;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class StoreDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from stores where store_id=" + id;
        try {
            String sqlSelect = "select store_id from store_inventory where store_id=" + id;

            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(stores sto) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[stores]
                   SET [store_name] = ?
                      ,[address] = ?
                      ,[phone_number] = ?
                      ,[email] = ?
                 WHERE store_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sto.getStore_name());
            pre.setString(2, sto.getAddress());
            pre.setString(3, sto.getPhone_number());
            pre.setString(4, sto.getEmail());
            pre.setInt(5, sto.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(stores sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           (store_id, [store_name]\n"
                + "           ,[address]\n"
                + "           ,[phone_number]\n"
                + "           ,[email])\n"
                + "     VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sto.getStore_id());
            pre.setString(2, sto.getStore_name());
            pre.setString(3, sto.getAddress());
            pre.setString(4, sto.getPhone_number());
            pre.setString(5, sto.getEmail());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<stores> getStore(String sql) {
        List<stores> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                stores sto = new stores(store_id, store_name, address, phone_number, email);
                list.add(sto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
