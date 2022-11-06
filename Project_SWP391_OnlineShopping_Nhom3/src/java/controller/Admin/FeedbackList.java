/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Feedback;
import entity.Product1;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Huynq
 */
public class FeedbackList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Use to process get feedback list and filter
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String status = request.getParameter("status")==null?"":request.getParameter("status");
        String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
        String rating = request.getParameter("rating")==null?"":request.getParameter("rating");
        String pageindex = request.getParameter("pageindex")==null||"".equals(request.getParameter("pageindex"))?"1":request.getParameter("pageindex");
        ProductDAO pdao = new ProductDAO();
        ArrayList<Product1> pl = pdao.getAllProduct(1, 999999);
        FeedbackDAO fdao = new FeedbackDAO();
        ArrayList<User> ul = fdao.getAllUser(1, 999999);
        ArrayList<Feedback> fl = fdao.getAllFeedback(status, pid, rating, Integer.valueOf(pageindex), 9);
        request.setAttribute("fl", fl);
        request.setAttribute("pl", pl);
        request.setAttribute("ul", ul);
        request.setAttribute("pageindex", pageindex);
        request.getRequestDispatcher("FeedbackList.jsp").forward(request, response);
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
