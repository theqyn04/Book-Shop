package controller;

import dao.OrderDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import model.orders;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        OrderDAO dao = new OrderDAO();
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "listOrder";
            }
            if (service.equals("deleteOrder")) {
                int oid = Integer.parseInt(request.getParameter("oid"));
                int n = dao.remove(oid);
                String deleteMessage;
                if (n == 0) {
                    deleteMessage = "Xóa thất bại";
                } else {
                    deleteMessage = "Xóa thành công";
                }

                response.sendRedirect("OrderURL");
            }
            if (service.equals("updateOrder")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// display form
                    int oid = Integer.parseInt(request.getParameter("oid"));
                    List<orders> list = dao.getOrder("select * from orders where order_id=" + oid);
                    if (list.get(0).getStatus().equals("done")) {
                        String msg = "Order cannot change!";
                        request.setAttribute("msg", msg);
                        request.getRequestDispatcher("jsp/CURD/errorlistOrder.jsp").forward(request, response);
                    }
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/jsp/CURD/updateOrder.jsp")
                            .forward(request, response);
                } else { //insert
                    int order_id = Integer.parseInt(request.getParameter("order_id"));
                    int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                    String orderDateStr = request.getParameter("order_date");
                    // Chuyển đổi chuỗi thành Timestamp
                    Timestamp order_date = Timestamp.valueOf(orderDateStr.replace("T", " ") + ":00");
                    double total_amount = Double.parseDouble(request.getParameter("total_amount"));
                    String status = request.getParameter("status");


                        orders ord = new orders(order_id, customer_id, order_date, total_amount, status);
                        dao.update(ord);


                    response.sendRedirect("OrderURL");
                }
            }
            if (service.equals("insertOrder")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // display form
                    request.getRequestDispatcher("/jsp/CURD/insertOrder.jsp")
                            .forward(request, response);
                } else { // insert
                    int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                    String orderDateStr = request.getParameter("order_date");
                    // Chuyển đổi chuỗi thành Timestamp
                    Timestamp order_date = Timestamp.valueOf(orderDateStr.replace("T", " ") + ":00");
                    double total_amount = Double.parseDouble(request.getParameter("total_amount"));
                    String status = request.getParameter("status");
                    orders ord = new orders(customer_id, order_date, total_amount, status);
                    dao.add(ord);
                    response.sendRedirect("OrderURL");
                }
            }
            if (service.equals("listOrder")) {
                //call model: get data
                List<orders> list;
                String submit = request.getParameter("submit");
                if (submit == null) {  //listAll
                    list = dao.getOrder("select * from orders");
                } else { //search
                    String cid = request.getParameter("cid");
                    list = dao.getOrder("select * from orders where customer_id =" + cid);
                }
                //set data to view (jsp)
                request.setAttribute("orderData", list);
                request.setAttribute("titleTable", "Order List");
                // select view (jsp)
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/CURD/listOrder.jsp");
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
