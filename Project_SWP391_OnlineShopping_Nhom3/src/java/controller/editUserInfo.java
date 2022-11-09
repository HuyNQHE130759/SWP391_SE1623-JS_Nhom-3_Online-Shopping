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
        String isEditSucess = "";
        boolean isOk = true;
//        //validate cho truong email
        Pattern patternEmail = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})*$", Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = patternEmail.matcher(request.getParameter("email"));
        if (!matcherEmail.find()) {
            isOk = false;
            isEditSucess += "Invalid email vd:abc@gmail.com\n";
        }
        //validate cho truong iphone chi dc nhap vaof so
        Pattern patternPhone = Pattern.compile("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})*$", Pattern.CASE_INSENSITIVE);
        Matcher matcherPhone = patternPhone.matcher(request.getParameter("phone"));
        if (!matcherPhone.find()) {
            isOk = false;
            isEditSucess += "Số điện thoại phải bắt đầu số 0 hoặc 84, và 10 chũ số\n";
        }
        //validate cho truong full name chi dc nhap vaof chu
        Pattern patternFullName = Pattern.compile("^(?![\\s.]+$)[a-zA-Z\\s.]*$", Pattern.CASE_INSENSITIVE);
        Matcher matcherFullName = patternFullName.matcher(request.getParameter("fullname").trim());
        if (!matcherFullName.find()) {
            isOk = false;
            isEditSucess += "Full name không chứa kí tự đặc biệt, tối đa là 5 từ\n";
        }
        //validate cho truong addrerr chi dc nhap chu va so
        Pattern patternAddress = Pattern.compile("^[a-zA-Z0-9 ]*$", Pattern.CASE_INSENSITIVE);
        Matcher matcherAddress = patternAddress.matcher(request.getParameter("address"));
        if (!matcherAddress.find()) {
            isOk = false;
            isEditSucess += "Địa chỉ không được chứa kí tự đăc biệt và chỉ được 40 kí tự\n";
        }
        if (isOk) {
            us.setEmail(request.getParameter("email").trim());
            us.setFullName(request.getParameter("fullname").trim());
            us.setPhone(request.getParameter("phone").trim());
            us.setAddress(request.getParameter("address").trim());
            us.setGender(Boolean.parseBoolean(request.getParameter("gender").trim()));
            System.out.println(request.getParameter("gender") + " " + us.isGender());
            request.getSession().setAttribute("user", us);
            dao.updateUserInfo(us);
            isEditSucess = "Cập nhật thành công";
        }
        request.setAttribute("errmsg", isEditSucess);
        request.setAttribute("isOk", isOk);
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
