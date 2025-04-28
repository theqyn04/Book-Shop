package controller;

import dao.BookDAO;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.books;


@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryURL"})
public class CategoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BookDAO dao = new BookDAO();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listBook";
            }
            if (service.equals("listBook")) {
                //call model: get data
                List<books> list;
                String id = request.getParameter("id");
                list = dao.getbook("select * from books"
                        + " where book_type_id=" + id);
//set data to view (jsp)
                request.setAttribute("bookTypeData", list);
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/bookType.jsp");
                //run (view)
                dispath.forward(request, response);
            }
            if (service.equals("listAllBook")) {
                List<books> list = dao.getbook("select * from books");
                request.setAttribute("bookTypeData", list);
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/bookType.jsp");
                //run (view)
                dispath.forward(request, response);
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
