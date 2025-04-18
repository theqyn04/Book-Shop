
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="CustomerCareController", urlPatterns={"/CustomerCareURL"})
public class CustomerCareController extends HttpServlet {
   
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");            
            
            switch (service) {
                case "introduce":
                    request.getRequestDispatcher("jsp/introducePage.jsp").forward(request, response);
                    break;
                case "contact":
                    request.getRequestDispatcher("jsp/contactPage.jsp").forward(request, response);
                    break;
                case "shipPolicy":
                    request.getRequestDispatcher("jsp/shipPolicyPage.jsp").forward(request, response);
                    break;
                case "faqs":
                    request.getRequestDispatcher("jsp/faqsPage.jsp").forward(request, response);
                    break;
                case "paymentMethod":
                    request.getRequestDispatcher("jsp/paymentMethodPage.jsp").forward(request, response);
                    break;
                case "infomationSecurity":
                    request.getRequestDispatcher("jsp/informationSecurityPage.jsp").forward(request, response);
                    break;
                case "refundPolicy":
                    request.getRequestDispatcher("jsp/refundPolicyPage.jsp").forward(request, response);
                    break;
                case "complaintHandling":
                    request.getRequestDispatcher("jsp/complaintHandlingPage.jsp").forward(request, response);
                    break;
                case "whyUShouldChooseShop":
                    request.getRequestDispatcher("jsp/whyUShouldChooseShopPage.jsp").forward(request, response);
                    break;
                default:
                    break;
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
