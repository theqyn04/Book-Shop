
package dao;

import model.book_types;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class BookTypeDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from book_types where book_type_id=" + id;
        try {
            String sqlSelect = "select book_type_id from books where book_type_id=" + id;

            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BookTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(book_types boo) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[book_types]
              SET [book_type_name] = ?
                 ,[description] = ?
            WHERE book_type_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, boo.getBook_type_name());
            pre.setString(2, boo.getDescription());
            pre.setInt(3, boo.getBook_type_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(book_types boo) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[book_types]\n"
                + "           (book_type_id, [book_type_name]\n"
                + "           ,[description])\n"
                + "     VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, boo.getBook_type_id());
            pre.setString(2, boo.getBook_type_name());
            pre.setString(3, boo.getDescription());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<book_types> getBookType(String sql) {
        List<book_types> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int book_type_id = rs.getInt("book_type_id");
                String book_type_name = rs.getString("book_type_name");
                String description = rs.getString("description");
                book_types boo = new book_types(book_type_id, book_type_name, description);
                list.add(boo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
