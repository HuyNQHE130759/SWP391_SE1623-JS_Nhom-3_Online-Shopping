/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.User;

public class ChangePass extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePass at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Changepassword.jsp").forward(request, response);
    }

    /**
     * this method allow to get parameter form user and update new pass to
     * database
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User us = (User) request.getSession().getAttribute("user");
        DAO dao = new DAO();

        HttpSession session = request.getSession();
        String oldpass = request.getParameter("oldpass").trim();
        String newpass = request.getParameter("newpass").trim();
        String renewpass = request.getParameter("renewpass").trim();
        User u = (User) session.getAttribute("user");
        //check old password match or not
        if (!oldpass.equals(u.getPassword())) {
            request.setAttribute("mess", "Old Password not match!");
            request.getRequestDispatcher("Changepassword.jsp").forward(request, response);
        } else if (!newpass.equals(renewpass)) {//check confirm password ok or not
            request.setAttribute("mess", "New password not match wwith re password");
            request.getRequestDispatcher("Changepassword.jsp").forward(request, response);
        } else {
            dao.changePassword(u.getCid(), newpass);
            us.setPassword(newpass);
            try ( PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password change successful!');");
                out.println("location='" + request.getContextPath() + "/HomePage';");
                out.println("</script>");
            }
//            response.sendRedirect("services");

//            request.getRequestDispatcher("Changepassword.jsp").forward(request, response);
        }

        //request.getSession().setAttribute("user", us);
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
