package controller;

import dao.StoreDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.stores;

@WebServlet(name="StoreController", urlPatterns={"/StoreURL"})
public class StoreController extends HttpServlet {
   
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StoreDAO dao = new StoreDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "listStore";
            }
            if(service.equals("deleteStore")){
                int sid=Integer.parseInt(request.getParameter("sid"));
                int n=dao.remove(sid);
                String deleteMessage;
                if(n==0){
                    deleteMessage="Xóa thất bại";
                }else{
                    deleteMessage="Xóa thành công";
                }
                
                response.sendRedirect("StoreURL");
            }
            if (service.equals("updateStore")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int sid=Integer.parseInt(request.getParameter("sid"));
                    List<stores> list = dao.getStore("select * from stores where store_id="+sid);
                    request.setAttribute("list", list);                    
                    request.getRequestDispatcher("/jsp/CURD/updateStore.jsp")
                            .forward(request, response);
                } else { //insert
                    int store_id = Integer.parseInt(request.getParameter("store_id"));
                    String store_name = request.getParameter("store_name");
                    String address = request.getParameter("address");
                    String phone_number = request.getParameter("phone_number");
                    String email = request.getParameter("email");
                    stores str = new stores(store_id, store_name, address, phone_number, email);
                    dao.update(str);
                    response.sendRedirect("StoreURL");
                }
            }
            if (service.equals("insertStore")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    
                    request.getRequestDispatcher("/jsp/CURD/insertStore.jsp")
                            .forward(request, response);
                } else { //insert
                    String store_name = request.getParameter("store_name");
                    String address = request.getParameter("address");
                    String phone_number = request.getParameter("phone_number");
                    String email = request.getParameter("email");
                    stores str = new stores(store_name, address, phone_number, email);
                    dao.add(str);
                    response.sendRedirect("StoreURL");
                }
            }
            if (service.equals("listStore")) {
                //call model: get data
                List<stores> list;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    list = dao.getStore("select * from stores");
                } else { //search
                    String sid = request.getParameter("sid");
                    list = dao.getStore("select * from stores where store_id ="+sid);
                }
                //set data to view (jsp)
                request.setAttribute("storeData", list);
                request.setAttribute("titleTable", "Store List");
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/CURD/listStore.jsp");
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
