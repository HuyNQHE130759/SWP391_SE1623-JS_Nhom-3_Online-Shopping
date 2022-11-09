/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.FeedbackDAO;
import entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class EditFeedback extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        //check login
        if (request.getSession().getAttribute("role") == null) {
            request.getSession().setAttribute("mess", "please login");
            response.sendRedirect("../Login");
            return;
        } else {
            //check role
            String role = request.getSession().getAttribute("role").toString();
            if (!role.equals("admin")) {
                request.getSession().setAttribute("mess", "you are not authorized");
                response.sendRedirect("../Login");
                return;
            }
        }

        String fid = request.getParameter("fid");
        FeedbackDAO fdao = new FeedbackDAO();
        //call method to get feedback detail
        Feedback f = fdao.getFeedback(fid);
        request.setAttribute("f", f);
        request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Post method use for edit
     * feedback
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fid = request.getParameter("fid");
        String comment = request.getParameter("comment");
        String rating = request.getParameter("rating");
        String img = request.getParameter("img");
        String status = request.getParameter("status");
        FeedbackDAO fdao = new FeedbackDAO();
        fdao.updateFeedback(comment, rating, img, status, fid);

        response.sendRedirect("./FeedbackList");
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
