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
import service.SendMail;

/**
 *
 * @author HuyNQ
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String fname = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String usname = request.getParameter("usname");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        DAO dao = new DAO();
        String resultMessage = "";
        if (dao.checkEmailExist(email)) {
            request.setAttribute("messRegister", "Email address alredy exist!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            dao.insertCustomer(fname, address, phone, usname, password, false, email, gender);
            try {
                SendMail sendmail = new SendMail();
                sendmail.send(email, "Create new account successfully!", "Please click link below to active your account<br> http://localhost:9999/ActiveAccount?email=" + email);
//            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
//                    content);
                resultMessage = "Create new account successfully!Please check your email";
            } catch (Exception ex) {
                ex.printStackTrace();
                try (PrintWriter out = response.getWriter()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert(" + "There were an error: " + ex.getMessage() + ");");
                    out.println("location='" + request.getContextPath() + "/Register';");
                    out.println("</script>");
                }
            } finally {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Create new account successfully!Please check your email');");
                    out.println("location='" + request.getContextPath() + "/HomePage';");
                    out.println("</script>");
                }
//                request.setAttribute("messRegister", resultMessage);
//                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
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
