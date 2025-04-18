package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.customers;
import model.orders;

@WebServlet(name = "AccountController", urlPatterns = {"/AccountURL"})
public class AccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if ("viewAccount".equals(service)) {
                int userId = Integer.parseInt(request.getParameter("id"));
                CustomerDAO dao = new CustomerDAO();
                OrderDAO daoOrd = new OrderDAO();
                customers customer = dao.getCustomerById(userId); // Phương thức này cần được thêm vào CustomerDAO
                if (customer == null) {
                    System.out.println("Customer is null"); // Log nếu customer là null
                } else {
                    System.out.println("Customer found: " + customer.getFull_name()); // Log tên khách hàng
                }
                List<orders> historyOrd = daoOrd.getOrder("select * from orders where customer_id="+userId);
                request.setAttribute("historyOrd", historyOrd);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
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
