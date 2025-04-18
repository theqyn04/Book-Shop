package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.BookDAO;
import java.util.List;
import model.books;

@WebServlet(name = "BookDetailController", urlPatterns = {"/BookDetailURL"})
public class BookDetailController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BookDAO dao = new BookDAO();
        int bid = Integer.parseInt(request.getParameter("bid")); // Lấy flower_id từ request

        // Lấy thông tin sản phẩm từ cơ sở dữ liệu
        books book = dao.getbookById(bid);

        // Truyền thông tin sản phẩm đến trang JSP
        request.setAttribute("book", book);
        
        List<books> listRelevant = dao.getbook("SELECT TOP 4 *\n"
                + "FROM books\n"
                + "WHERE book_type_id = (select book_type_id from books where book_id ="+bid+")\n"
                + "ORDER BY NEWID();");
        request.setAttribute("listRelevant", listRelevant);
        
        request.getRequestDispatcher("jsp/bookDetail.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
