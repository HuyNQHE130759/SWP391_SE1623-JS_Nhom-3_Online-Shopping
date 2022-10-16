/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
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
import entity.User;

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
//        boolean isOk = true;
//        //validate cho truong email
//        Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//        Matcher matcherEmail = patternEmail.matcher(request.getParameter("email"));
//        if (!matcherEmail.find()) {
//            isOk = false;
//            try ( PrintWriter out = response.getWriter()) {
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('Invalid Email format');");
//                out.println("location='" + request.getContextPath() + "/AccountInfo';");
//                out.println("</script>");
//            }
//        }
        //validate cho truong iphone chi dc nhap vaof so
//        Pattern patternPhone = Pattern.compile("^\\d{10}$", Pattern.CASE_INSENSITIVE);
//        Matcher matcherPhone = patternPhone.matcher(request.getParameter("phone"));
//        if (!matcherPhone.find()) {
//            isOk = false;
//            try ( PrintWriter out = response.getWriter()) {
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('Phone must have 10 character');");
//                out.println("location='" + request.getContextPath() + "/AccountInfo';");
//                out.println("</script>");
//            }
//
//        }
        //validate cho truong full name chi dc nhap vaof chu
        //  Pattern patternFullName = Pattern.compile("^[a-zA-Z ]*$", Pattern.CASE_INSENSITIVE);
        //  Matcher matcherFullName = patternFullName.matcher(request.getParameter("fullname"));
        //   if (!matcherFullName.find()) {
        //      isOk = false;
        // try ( PrintWriter out = response.getWriter()) {
        // out.println("<script type=\"text/javascript\">");
        //  out.println("alert('Full name without special numbers and characters');");
        // out.println("location='" + request.getContextPath() + "/AccountInfo';");
        //   out.println("</script>");
        //  }

        //  }
        //validate cho truong addrerr chi dc nhap chu va so
//        Pattern patternAddress = Pattern.compile("^[a-zA-Z0-9 ]*$", Pattern.CASE_INSENSITIVE);
//        Matcher matcherAddress = patternAddress.matcher(request.getParameter("address"));
//        if (!matcherAddress.find()) {
//            isOk = false;
//            try ( PrintWriter out = response.getWriter()) {
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('Must not contain special characters');");
//                out.println("location='" + request.getContextPath() + "/AccountInfo';");
//                out.println("</script>");
//            }
//
//        }
        us.setEmail(request.getParameter("email"));
        us.setFullName(request.getParameter("fullname"));
        us.setPhone(request.getParameter("phone"));
        us.setAddress(request.getParameter("address"));
        us.setGender(Boolean.parseBoolean(request.getParameter("gender")));
        System.out.println(request.getParameter("gender") + " " + us.isGender());
        request.getSession().setAttribute("user", us);
        dao.updateUserInfo(us);
        request.setAttribute("isEditSuccess", "Cập nhật thành công");

        request.getRequestDispatcher("AccountInfo.jsp").forward(request, response);
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
