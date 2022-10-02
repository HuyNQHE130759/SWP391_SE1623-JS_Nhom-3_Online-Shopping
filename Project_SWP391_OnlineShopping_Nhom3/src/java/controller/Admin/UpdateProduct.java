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
import entity.Product;


public class UpdateProduct extends BasedRequiredAuthenticationController1 {

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
            out.println("<title>Servlet UpdateProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath() + "</h1>");
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
        String pid = request.getParameter("pid");
        cal = dao.getCategory();
        Product p = dao.getProductById(pid);
        request.setAttribute("categoryList", cal);
        request.setAttribute("product", p);
        request.setAttribute("pid", pid);
        request.getRequestDispatcher("update_product.jsp").forward(request, response);
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
        String pid = request.getParameter("ppid");
        String pname = request.getParameter("name");
        int pquantity = Integer.parseInt(request.getParameter("quantity"));
        int pprice = Integer.parseInt(request.getParameter("money"));
        String pimage = request.getParameter("Image");
        String pdescription = request.getParameter("description");
        String pstatus = request.getParameter("status");
        boolean status;
        if(pstatus!= null){
            status = true;
        }else{
            status = false;
        }
        String CateID = request.getParameter("cateID");

        DAO dao = new DAO();
        dao.updateProduct(pid, pname, pquantity, pprice, pimage, pdescription, status, CateID);
        response.sendRedirect(request.getContextPath() + "/AdminConsole");
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
