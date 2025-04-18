
package controller;

import dao.BookTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.book_types;


@WebServlet(name="CURDCategoryController", urlPatterns={"/CURDCategoryURL"})
public class CURDCategoryController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BookTypeDAO dao = new BookTypeDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "listBookType";
            }
            if(service.equals("deleteBookType")){
                int bid=Integer.parseInt(request.getParameter("bid"));
                int n=dao.remove(bid);
                String deleteMessage;
                if(n==0){
                    deleteMessage="Xóa thất bại";
                }else{
                    deleteMessage="Xóa thành công";
                }
                
                response.sendRedirect("CURDCategoryURL");
            }
            if (service.equals("updateBookType")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int bid=Integer.parseInt(request.getParameter("bid"));
                    List<book_types> list = dao.getBookType("select * from book_types where book_type_id="+bid);
                    request.setAttribute("list", list);                    
                    request.getRequestDispatcher("/jsp/CURD/updateBookType.jsp")
                            .forward(request, response);
                } else { //insert
                    int book_type_id = Integer.parseInt(request.getParameter("book_type_id"));
                                     
                    String book_type_name = request.getParameter("book_type_name");
                    
                    String description = request.getParameter("description");
                    book_types boo = new book_types(book_type_id, book_type_name, description);
                    dao.update(boo);
                    response.sendRedirect("CURDCategoryURL");
                }
            }
            if (service.equals("insertBookType")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    
                    request.getRequestDispatcher("/jsp/CURD/insertBookType.jsp")
                            .forward(request, response);
                } else { //insert
                    int book_type_id = Integer.parseInt(request.getParameter("book_type_id"));
                                     
                    String book_type_name = request.getParameter("book_type_name");
                    
                    String description = request.getParameter("description");
                    book_types boo = new book_types(book_type_id, book_type_name, description);
                    dao.add(boo);
                    response.sendRedirect("CURDCategoryURL");
                }
            }
            if (service.equals("listBookType")) {
                //call model: get data
                List<book_types> list;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    list = dao.getBookType("select * from book_types");
                } else { //search
                    String bid = request.getParameter("bid");
                    list = dao.getBookType("select * from book_type where book_type_id ="+bid);
                }
                //set data to view (jsp)
                request.setAttribute("bookTypeData", list);
                request.setAttribute("titleTable", "Book Type List");
                // select view (jsp)
                request.getRequestDispatcher("/jsp/CURD/listBookType.jsp").forward(request, response);

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
