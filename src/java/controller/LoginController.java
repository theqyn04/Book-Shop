package controller;

import dao.CustomerDAO;
import dao.EmployeesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import model.customers;
import model.employees;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginURL"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CustomerDAO dao = new CustomerDAO();
        EmployeesDAO empDao = new EmployeesDAO();
        HttpSession session = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "loginCustomer";
            }
            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String userType = request.getParameter("userType");

                    if (userType.equals("employee")) {
                        employees emp = empDao.loginEmployee(username, password);
                        if (emp != null) {

                            // Lưu thông tin nhân viên vào session
                            session.setAttribute("userName", emp.getUsername());
                            session.setAttribute("userId", emp.getEmployee_id()); // Lưu id của nhân viên
                            request.getRequestDispatcher("jsp/employeeDashboard.jsp").forward(request, response);
                        } else {
                            request.setAttribute("message", "Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản và mật khẩu.");
                            request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);

                        }
                    } else {
                        // Gọi phương thức loginCustomer mới
                        customers cus = dao.loginCustomer(username, password);
                        if (cus != null) {
                            // Lưu thông tin người dùng vào session
                            session.setAttribute("loginMessage", "Đăng nhập thành công!");
                            session.setAttribute("userName", cus.getUsername());
                            session.setAttribute("userId", cus.getCustomer_id()); // Lưu id của customer
                            // Chuyển giỏ hàng từ session cũ sang session mới (nếu có)
                            Enumeration<String> keys = session.getAttributeNames();
                            while (keys.hasMoreElements()) {
                                String key = keys.nextElement();
                                if (key.startsWith("cart_")) { // Giả sử giỏ hàng được lưu với key bắt đầu bằng "cart_"
                                    Object cartItem = session.getAttribute(key);
                                    session.setAttribute(key, cartItem); // Giữ nguyên giỏ hàng
                                }
                            }
                            response.sendRedirect("HomeURL");
                        } else {
                            request.setAttribute("message", "Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản và mật khẩu.");
                            request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);
                        }
                    }

                } else {
                    // Xử lý trường hợp không có submit (ví dụ: truy cập trực tiếp URL)
                    request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);
                }
            }

            if (service.equals("registerCustomer")) {
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String full_name = request.getParameter("full_name");
                    String address = request.getParameter("address");
                    String phone_number = request.getParameter("phone_number");
                    String email = request.getParameter("email");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    // Kiểm tra các trường bắt buộc
                    if (full_name == null || full_name.trim().isEmpty()
                            || address == null || address.trim().isEmpty()
                            || phone_number == null || phone_number.trim().isEmpty()
                            || username == null || username.trim().isEmpty()
                            || password == null || password.trim().isEmpty()) {

                        // Hiển thị thông báo lỗi nếu có trường bị bỏ trống
                        request.setAttribute("errorMessage", "All fields are required. Please fill out all fields.");
                        request.getRequestDispatcher("/jsp/registerCustomer.jsp").forward(request, response);
                    } else {
                        // Kiểm tra xem username đã tồn tại chưa
                        if (dao.isUsernameExists(username)) {
                            // Hiển thị thông báo lỗi nếu username đã tồn tại
                            request.setAttribute("errorMessage", "Tài khoản đã tồn tại.");
                            request.getRequestDispatcher("/jsp/registerCustomer.jsp").forward(request, response);
                        } else {
                            // Nếu tất cả các trường hợp lệ và username không trùng, tiếp tục xử lý đăng ký
                            customers cus = new customers(full_name, address, phone_number, email, username, password);
                            dao.add(cus);
                            response.sendRedirect("LoginURL?service=loginCustomer");
                        }
                    }
                } else {
                    request.getRequestDispatcher("/jsp/registerCustomer.jsp").forward(request, response);
                }
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
