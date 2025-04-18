package dao;

import model.books;
import model.cart;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class BookDAO extends DBConnect {

    //DBConnect dbConnect=new DBConnect();
    public int remove(int id) {
        int n = 0;
        String sql = "delete from books where book_id=" + id;
        try {
            String sqlSelect = "select book_id from order_details where book_id=" + id;
            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {// foreign key constrain --> don't delete
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(books boo) {
        int n = 0;
        String sql = """
           UPDATE [dbo].[books]
              SET [book_name] = ?
                 ,[book_type_id] = ?
                 ,[price] = ?
                 ,[stock_quantity] = ?
                 ,[description] = ?
                 ,[imageURL] = ?
            WHERE book_id = ?
              """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, boo.getBook_name());
            pre.setInt(2, boo.getBook_type_id());
            pre.setDouble(3, boo.getPrice());
            pre.setInt(4, boo.getStock_quantity());
            pre.setString(5, boo.getDescription());
            pre.setString(6, boo.getImageURL());
            pre.setInt(7, boo.getBook_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int add(books boo) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[books]\n"
                + "           ([book_name]\n"
                + "           ,[book_type_id]\n"
                + "           ,[price]\n"
                + "           ,[stock_quantity]\n"
                + "           ,[description]\n"
                + "           ,[imageURL])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, boo.getBook_name());
            pre.setInt(2, boo.getBook_type_id());
            pre.setDouble(3, boo.getPrice());
            pre.setInt(4, boo.getStock_quantity());
            pre.setString(5, boo.getDescription());
            pre.setString(6, boo.getImageURL());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<books> getbook(String sql) {
        List<books> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {

                int book_id = rs.getInt("book_id");
                String book_name = rs.getString("book_name");
                int book_type_id = rs.getInt("book_type_id");
                double price = rs.getDouble("price");
                int stock_quantity = rs.getInt("stock_quantity");
                String description = rs.getString("description");
                String imageURL = rs.getString("imageURL");
                books boo = new books(book_id, book_name, book_type_id, price, stock_quantity, description, imageURL);
                list.add(boo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<cart> getbookCart(String sql) {
        List<cart> list = new ArrayList<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {;
                int book_id = rs.getInt("book_id");
                String book_name = rs.getString("book_name");
                int book_type_id = rs.getInt("book_type_id");
                double price = rs.getDouble("price");

                String imageURL = rs.getString("imageURL");
                cart cart = new cart(book_id, book_name, book_type_id, price, 1, imageURL, 0);
                list.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public books getbookById(int bookId) {
        books book = null;
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new books();
                book.setBook_id(rs.getInt("book_id"));
                book.setBook_name(rs.getString("book_name"));
                book.setPrice(rs.getDouble("price"));
                book.setImageURL(rs.getString("imageURL"));
                book.setDescription(rs.getString("description"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return book;
    }
    


    private double calculateTotalAmount(List<cart> cartItems) {
        double total = 0;
        for (cart item : cartItems) {
            total += item.getQuantity() * item.getPrice();
        }
        return total;
    }

    public void createOrder(int customerId, List<cart> cartItems) {
        try {
            double totalAmount = calculateTotalAmount(cartItems);

            String sql = "INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerId); 
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setDouble(3, totalAmount);
            ps.setString(4, "processing");
            ps.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
   

}
