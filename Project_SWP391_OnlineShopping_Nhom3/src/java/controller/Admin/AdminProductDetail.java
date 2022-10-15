/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Admin;

import dao.DAO;
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
        String pid = request.getParameter("id");
        String name = request.getParameter("name");
        int num = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("money"));
        String img = request.getParameter("Image");
        String description = request.getParameter("description");
        String cid = request.getParameter("cateID");
        String Sstatus = request.getParameter("status");
        boolean status;
        if(Sstatus != null){
            status = true;
        }else
            status = false;
        dao.insertProduct(pid, name, img, num, price, description, status, cid);
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
