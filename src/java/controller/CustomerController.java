package controller;

import dao.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.customers;

@WebServlet(name="CustomerController", urlPatterns={"/CustomerURL"})
public class CustomerController extends HttpServlet {
   
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CustomerDAO dao = new CustomerDAO();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listCustomer";
            }
            if(service.equals("deleteCustomer")){
                int cid=Integer.parseInt(request.getParameter("cid"));
                int n=dao.remove(cid);
                String deleteMessage;
                if(n==0){
                    deleteMessage="delete faile";
                }else{
                    deleteMessage="delete completed";
                }
                
                response.sendRedirect("CustomerURL");
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int cid=Integer.parseInt(request.getParameter("cid"));
                    List<customers> list
                            = dao.getCustomers("select * from customers"
                                    + " where customer_id="+cid);
                    request.setAttribute("list", list);
                    
                    request.getRequestDispatcher("/jsp/CURD/updateCustomer.jsp")
                            .forward(request, response);
                } else { //insert
                    int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                    String full_name = request.getParameter("full_name");
                    String address = request.getParameter("address");
                    String phone_number = request.getParameter("phone_number");
                    String email = request.getParameter("email");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    customers cus = new customers(customer_id, full_name, address, phone_number, email, username, password);
                    dao.update(cus);
                    response.sendRedirect("CustomerURL");
                }
            }
            if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    
                    request.getRequestDispatcher("/jsp/CURD/insertCustomer.jsp")
                            .forward(request, response);
                } else { //insert
                    String full_name = request.getParameter("full_name");
                    String address = request.getParameter("address");
                    String phone_number = request.getParameter("phone_number");
                    String email = request.getParameter("email");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    customers cus = new customers(full_name, address, phone_number, email, username, password);
                    dao.add(cus);
                    response.sendRedirect("CustomerURL");
                }
            }
            if (service.equals("listCustomer")) {
                //call model: get data
                List<customers> listCus;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    listCus = dao.getCustomers("select * from customers");
                } else { //search
                    String cname = request.getParameter("cname");
                    listCus
                            = dao.getCustomers("select * from customers"
                                    + " where full_name like '%" + cname + "%'");
                }
//set data to view (jsp)
                request.setAttribute("customerData", listCus);
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/CURD/listCustomer.jsp");
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
