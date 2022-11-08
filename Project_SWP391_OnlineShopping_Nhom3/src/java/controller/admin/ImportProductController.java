/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ImportDAO;
import dao.ImportDAOInterface;
import dao.ProductDAO;
import dao.ProductDAOInterface;
import entity.Product1;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author apc
 */
public class ImportProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ProductDAOInterface productDAO = new ProductDAO();
    ImportDAOInterface importDAO = new ImportDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_pid = request.getParameter("pid").trim();
        request.setAttribute("pid", raw_pid);
        request.setAttribute("products", productDAO.getAllProduct());
        request.getRequestDispatcher("../ImportProduct.jsp").forward(request, response);
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
        String raw_pid = request.getParameter("product").trim();
        String raw_quantity = request.getParameter("quantity").trim();
        int quantity = Integer.parseInt(raw_quantity);
        Integer pid = (raw_pid != null && raw_pid.length() > 0) ? new Integer(raw_pid) : null;
        Product1 pd = productDAO.getProductById(pid);
        Date date = Date.valueOf(LocalDate.now());
        importDAO.insert(pd, pd.getProvider(), quantity, date);
        response.sendRedirect(request.getContextPath() + "/Import/list");
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
