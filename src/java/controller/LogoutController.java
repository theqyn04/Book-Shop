package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutController", urlPatterns = {"/LogoutURL"})
public class LogoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy session hiện tại
        HttpSession session = request.getSession(false); // false: không tạo session mới nếu không tồn tại

        if (session != null) {
            // Xóa thông tin người dùng khỏi session
            session.removeAttribute("userName");
            session.removeAttribute("userId");

            // Hủy toàn bộ session (tùy chọn)
            session.invalidate();
        }

        // Chuyển hướng về trang chủ hoặc trang đăng nhập
        response.sendRedirect("HomeURL"); // Thay "HomeURL" bằng đường dẫn phù hợp
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}