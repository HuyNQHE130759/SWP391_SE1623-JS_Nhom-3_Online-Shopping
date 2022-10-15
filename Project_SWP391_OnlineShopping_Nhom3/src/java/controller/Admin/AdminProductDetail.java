/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Admin;

import dao.DAO;
import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author apc
 */
public class AdminProductDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DAO dao = new DAO();
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String pid = request.getParameter("pid");
        request.setAttribute("categoryList", dao.getCategory());
        request.setAttribute("product", dao.getProductById(pid));
        request.setAttribute("pid", pid);
        request.getRequestDispatcher("../AdminProductDetail.jsp").forward(request,response);
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
        String raw_pid = request.getParameter("pid");
        String raw_pname = request.getParameter("pname");
        String raw_price = request.getParameter("price");
        String raw_img = request.getParameter("image");
        String raw_description = request.getParameter("description");
        String raw_cid = request.getParameter("category");
        String raw_status = request.getParameter("status");
        boolean status = Boolean.parseBoolean(raw_status);
        float price = Float.parseFloat(raw_price);
        int cid = Integer.parseInt(raw_cid);
        Integer pid = (raw_pid !=null && raw_pid.length()>0)?new Integer(raw_pid):null;
        if (pid != null) {
            productDAO.insert(raw_pname, raw_img, price, raw_description, status, cid);
        }else{
            productDAO.update(pid, raw_pname, raw_img, price, raw_description, status, cid);
        }
        response.sendRedirect(request.getContextPath() + "/AdminProduct/list");
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
