package controller;

import dao.StoreReventoryDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.store_inventory;

@WebServlet(name="StoreInventoryController", urlPatterns={"/StoreInventoryURL"})
public class StoreInventoryController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StoreReventoryDAO dao = new StoreReventoryDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "listStoreInventory";
            }
            if(service.equals("deleteStoreInventory")){
                int sid=Integer.parseInt(request.getParameter("sid"));
                int n=dao.remove(sid);
                String deleteMessage;
                if(n==0){
                    deleteMessage="Xóa thất bại";
                }else{
                    deleteMessage="Xóa thành công";
                }
                
                response.sendRedirect("StoreInventoryURL");
            }
            if (service.equals("updateStoreInventory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int sid=Integer.parseInt(request.getParameter("sid"));
                    List<store_inventory> list = dao.getStoreInventory("select * from store_inventory where id="+sid);
                    request.setAttribute("list", list);                    
                    request.getRequestDispatcher("/jsp/CURD/updateStoreInventory.jsp")
                            .forward(request, response);
                } else { //insert
                    int id = Integer.parseInt(request.getParameter("id"));
                    int store_id = Integer.parseInt(request.getParameter("store_id"));
                    int book_id = Integer.parseInt(request.getParameter("book_id"));
                    int stock_quantity = Integer.parseInt(request.getParameter("stock_quantity"));
                    store_inventory sto = new store_inventory(id, store_id, book_id, stock_quantity);
                    dao.update(sto);
                    response.sendRedirect("StoreInventoryURL");
                }
            }
            if (service.equals("insertStoreInventory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    
                    request.getRequestDispatcher("/jsp/CURD/insertStoreInventory.jsp")
                            .forward(request, response);
                } else { //insert
                    int store_id = Integer.parseInt(request.getParameter("store_id"));
                    int book_id = Integer.parseInt(request.getParameter("book_id"));
                    int stock_quantity = Integer.parseInt(request.getParameter("stock_quantity"));
                    store_inventory sto = new store_inventory(store_id, book_id, stock_quantity);
                    dao.add(sto);
                    response.sendRedirect("StoreInventoryURL");
                }
            }
            if (service.equals("listStoreInventory")) {
                //call model: get data
                List<store_inventory> list;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    list = dao.getStoreInventory("select * from store_inventory");
                } else { //search
                    String sid = request.getParameter("sid");
                    list = dao.getStoreInventory("select * from store_inventory where id ="+sid);
                }
                //set data to view (jsp)
                request.setAttribute("storeInventoryData", list);
                request.setAttribute("titleTable", "Store Inventory List");
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/CURD/listStoreInventory.jsp");
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
