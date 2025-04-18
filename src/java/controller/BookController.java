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

@WebServlet(name="BookController", urlPatterns={"/BookURL"})
public class BookController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BookDAO dao = new BookDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "listBook";
            }
            if(service.equals("deleteBook")){
                int bid=Integer.parseInt(request.getParameter("bid"));
                int n=dao.remove(bid);
                String deleteMessage;
                if(n==0){
                    deleteMessage="Xóa thất bại";
                }else{
                    deleteMessage="Xóa thành công";
                }
                
                response.sendRedirect("BookURL");
            }
            if (service.equals("updateBook")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int bid=Integer.parseInt(request.getParameter("bid"));
                    List<books> list = dao.getbook("select * from books where book_id="+bid);
                    request.setAttribute("list", list);                    
                    request.getRequestDispatcher("/jsp/CURD/updateBook.jsp")
                            .forward(request, response);
                } else { //insert
                    int book_id = Integer.parseInt(request.getParameter("book_id"));
                    String book_name = request.getParameter("book_name");
                    int book_type_id = Integer.parseInt(request.getParameter("book_type_id"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    int stock_quantity = Integer.parseInt(request.getParameter("stock_quantity"));
                    String description = request.getParameter("description");
                    String imageURL = request.getParameter("imageURL");
                    books boo = new books(book_id, book_name, book_type_id, price, stock_quantity, description, imageURL);
                    dao.update(boo);
                            
                    response.sendRedirect("BookURL");
                }
            }
            if (service.equals("insertBook")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    
                    request.getRequestDispatcher("/jsp/CURD/insertBook.jsp")
                            .forward(request, response);
                } else { //insert
                    String book_name = request.getParameter("book_name");
                    int book_type_id = Integer.parseInt(request.getParameter("book_type_id"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    int stock_quantity = Integer.parseInt(request.getParameter("stock_quantity"));
                    String description = request.getParameter("description");
                    String imageURL = request.getParameter("imageURL");
                    books boo = new books(book_name, book_type_id, price, stock_quantity, description, imageURL);
                    dao.add(boo);
                            
                    response.sendRedirect("BookURL");
                }
            }
            if (service.equals("listBook")) {
                //call model: get data
                List<books> list;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    list = dao.getbook("select * from books");
                } else { //search
                    String bname = request.getParameter("bname");
                    list = dao.getbook("select * from books where book_name like '%"+bname+"%'");
                }
                                
                request.setAttribute("titleTable", "Book List");
                // select view (jsp)
                request.setAttribute("bookData", list);
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/CURD/listBook.jsp");
                //run (view)
                dispath.forward(request, response);

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
