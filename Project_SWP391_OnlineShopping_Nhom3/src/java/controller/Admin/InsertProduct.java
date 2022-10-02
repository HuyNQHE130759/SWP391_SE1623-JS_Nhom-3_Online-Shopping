/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import controller.BasedRequiredAuthenticationController1;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Category;

/**
 *
 * @author HuyNQ
 */
public class InsertProduct extends BasedRequiredAuthenticationController1 {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
     DAO dao = new DAO();
     ArrayList<Category> cal = new ArrayList<>();
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cal = dao.getCategory();
        request.setAttribute("categoryList", cal);
        request.getRequestDispatcher("insertProduct.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
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
        response.sendRedirect(request.getContextPath() + "/AdminProductList");
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
