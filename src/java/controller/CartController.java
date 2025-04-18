package controller;

import model.cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import dao.BookDAO;
import java.util.ArrayList;

@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        BookDAO dao = new BookDAO();
        String service = request.getParameter("service");
        try (PrintWriter out = response.getWriter()) {
            if (service == null) {
                service = "showCart";
            }
            if (service.equals("showCart")) {
                List<cart> cartList = new ArrayList<>();
                double totalAmount = 0; // Biến để tính tổng số tiền
                Enumeration<String> keys = session.getAttributeNames();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    if (key.startsWith("cart_")) { // Lấy tất cả sản phẩm trong giỏ hàng
                        cart cartItem = (cart) session.getAttribute(key);
                        cartList.add(cartItem);
                        totalAmount += cartItem.getQuantity() * cartItem.getPrice(); // Tính tổng số tiền
                    }
                }
                request.setAttribute("listCart", cartList);
                request.setAttribute("totalAmount", totalAmount); // Truyền tổng số tiền đến JSP
                request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
            }

            if (service.equals("add2cart")) {
                int bid = Integer.parseInt(request.getParameter("bid"));
                cart cart = (cart) session.getAttribute("cart_" + bid); // Sử dụng key cụ thể
                if (cart == null) {
                    List<cart> list = dao.getbookCart("select * from books where book_id=" + bid);
                    if (!list.isEmpty()) {
                        cart = list.get(0);
                        cart.setQuantity(1); // Đặt số lượng mặc định là 1
                        session.setAttribute("cart_" + bid, cart); // Lưu vào session với key cụ thể
                    }
                } else {
                    cart.setQuantity(cart.getQuantity() + 1); // Tăng số lượng nếu sản phẩm đã có trong giỏ
                }
                response.sendRedirect("HomeURL"); // Chuyển hướng về trang chủ
            }

            if (service.equals("removeBook")) {
                int bid = Integer.parseInt(request.getParameter("bid")); // Lấy flower_id từ request
                session.removeAttribute("cart_" + bid); // Xóa sản phẩm khỏi session
                response.sendRedirect("CartURL?service=showCart"); // Chuyển hướng về trang giỏ hàng
            }

            if (service.equals("removeAll")) {
                // Xóa tất cả sản phẩm trong giỏ hàng
                Enumeration<String> keys = session.getAttributeNames();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    if (key.startsWith("cart_")) { // Xóa tất cả sản phẩm trong giỏ hàng
                        session.removeAttribute(key);
                    }
                }
                response.sendRedirect("CartURL?service=showCart"); // Chuyển hướng về trang giỏ hàng
            }

            if (service.equals("checkout")) {
                // Kiểm tra xem người dùng đã đăng nhập chưa
                if (session.getAttribute("userId") == null) {
                    // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập hoặc hiển thị thông báo
                    request.getRequestDispatcher("jsp/needToLogin.jsp").forward(request, response);
                 
                }

                // Lấy danh sách sản phẩm trong giỏ hàng
                Enumeration<String> keyList = session.getAttributeNames();
                List<cart> list = new ArrayList<cart>();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    if (key.startsWith("cart_")) { // Lấy tất cả sản phẩm trong giỏ hàng
                        cart cart = (cart) session.getAttribute(key);
                        list.add(cart);
                    }
                }

                // Lấy customerId từ session
                int customerId = (int) session.getAttribute("userId");

                // Tạo đơn hàng và lưu vào cơ sở dữ liệu               
                dao.createOrder(customerId, list);

                // Xóa giỏ hàng sau khi thanh toán
                Enumeration<String> keys = session.getAttributeNames();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    if (key.startsWith("cart_")) { // Xóa tất cả sản phẩm trong giỏ hàng
                        session.removeAttribute(key);
                    }
                }

                // Chuyển hướng đến trang cảm ơn
                response.sendRedirect("HomeURL"); // Sử dụng sendRedirect thay vì forward
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
