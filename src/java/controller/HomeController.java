package controller;

import model.books;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import dao.BookDAO;

@WebServlet(name = "HomeController", urlPatterns = {"/HomeURL"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BookDAO dao = new BookDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "home";
            }
            if (service.equals("home")) {

                //chọn ra 8 loại hoa bán chạy nhất
                List<books> listBestOrder = dao.getbook("SELECT TOP 8 *\n"
                        + "FROM books\n"
                        + "ORDER BY stock_quantity DESC;");
                request.setAttribute("bestOrder", listBestOrder);

                //chọn ra sản phẩm mới theo id giảm dần
                List<books> listNewBook = dao.getbook("SELECT TOP 8 *\n"
                        + "FROM books\n"
                        + "ORDER BY book_id DESC;");
                request.setAttribute("newBook", listNewBook);
                

                //chọn ra ngẫu nhiên 4 dòng với type_id = 1
                List<books> listLiteraty = dao.getbook("SELECT TOP 4 *\n"
                        + "FROM books\n"
                        + "WHERE book_type_id = 1\n"
                        + "ORDER BY NEWID();");
                request.setAttribute("listLiteraty", listLiteraty);
                request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
