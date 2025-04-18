
package dao;

import model.store_inventory;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class StoreReventoryDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from store_reventory where id=" + id;
        try {

            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreReventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(store_inventory sto) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[store_inventory]
                  SET [store_id] = ?
                     ,[book_id] = ?
                     ,[stock_quantity] = ?
                WHERE id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sto.getStore_id());
            pre.setInt(2, sto.getBook_id());
            pre.setInt(3, sto.getStock_quantity());

            pre.setInt(4, sto.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreReventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(store_inventory sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[store_inventory]\n"
                + "           (id, [store_id]\n"
                + "           ,[book_id]\n"
                + "           ,[stock_quantity])\n"
                + "     VALUES (?,?,?,?";
        try {
            //         ? by fields of table
//         ? start from 1
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sto.getId());
            pre.setInt(2, sto.getStore_id());
            pre.setInt(3, sto.getBook_id());
            pre.setInt(4, sto.getStock_quantity());

            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreReventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<store_inventory> getStoreInventory(String sql) {
        List<store_inventory> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int id = rs.getInt("id");
                int store_id = rs.getInt("store_id");
                int book_id = rs.getInt("book_id");
                int stock_quantity = rs.getInt("stock_quantity");
                store_inventory sto = new store_inventory(id, store_id, book_id, stock_quantity);
                list.add(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreReventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
