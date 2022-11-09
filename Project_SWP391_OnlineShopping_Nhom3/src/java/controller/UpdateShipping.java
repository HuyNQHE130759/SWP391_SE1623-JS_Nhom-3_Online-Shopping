package controller;

import dao.ShippingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateShipping", urlPatterns = {"/UpdateShipping"})
public class UpdateShipping extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String billId = request.getParameter("billId");
            String value = request.getParameter("selectStatusShipping");
            ShippingDAO shippingDAO = new ShippingDAO();
            if(billId != null) {
                shippingDAO.updateShippingStatus(Integer.parseInt(billId), value);
            }
            request.getRequestDispatcher("ShippingList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
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
        request.setAttribute("id", request.getParameter("id"));
        request.getRequestDispatcher("UpdateShipping.jsp").forward(request, response);
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
        try {
            String billId = request.getParameter("id");
            String value = request.getParameter("selectStatusShipping");
            ShippingDAO shippingDAO = new ShippingDAO();
            if(billId != null) {
                int result = shippingDAO.updateShippingStatus(Integer.parseInt(billId), value);
                if(result == 1 && value.equals("done")) {
                    shippingDAO.updateQuantity(shippingDAO.getProductIdByBillId(Integer.parseInt(billId)), Integer.parseInt(billId));
                }
            }
            response.sendRedirect("ShippingList?page=1");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
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
