/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet(name = "editUserInfo", urlPatterns = {"/editUserInfo"})
public class editUserInfo extends HttpServlet {

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
            out.println("<title>Servlet editUserInfo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editUserInfo at " + request.getContextPath() + "</h1>");
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
        User us = (User) request.getSession().getAttribute("user");
        DAO dao = new DAO();
        boolean isOk = true;
        Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = patternEmail.matcher(request.getParameter("email"));
        if (!matcherEmail.find()) {
            isOk = false;
            try ( PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid Email format');");
                out.println("location='" + request.getContextPath() + "/AccountInfo';");
                out.println("</script>");
            }
        }
        Pattern patternPhone = Pattern.compile("^\\d{10}$", Pattern.CASE_INSENSITIVE);
        Matcher matcherPhone = patternPhone.matcher(request.getParameter("phone"));
        if (!matcherPhone.find()) {
            isOk = false;
            try ( PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Phone must have 10 character');");
                out.println("location='" + request.getContextPath() + "/AccountInfo';");
                out.println("</script>");
            }
        }

        if (isOk) {
            us.setEmail(request.getParameter("email"));
            us.setFullName(request.getParameter("fullname"));
            us.setPhone(request.getParameter("phone"));
            us.setAddress(request.getParameter("address"));
            us.setGender(Boolean.parseBoolean(request.getParameter("gender")));
            request.getSession().setAttribute("user", us);
            dao.updateUserInfo(us);
            try ( PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Change successfully!');");
                out.println("location='" + request.getContextPath() + "/AccountInfo';");
                out.println("</script>");
            }
        }
        response.sendRedirect(request.getContextPath() + "/AccountInfo");
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
