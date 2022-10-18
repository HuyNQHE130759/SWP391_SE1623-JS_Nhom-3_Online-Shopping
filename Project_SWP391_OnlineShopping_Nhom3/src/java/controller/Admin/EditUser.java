/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import dao.DAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author sango
 */
@WebServlet(name = "EditUser", urlPatterns = {"/EditUser"})
public class EditUser extends HttpServlet {

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
            out.println("<title>Servlet EditUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUser at " + request.getContextPath() + "</h1>");
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
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUserById(Integer.parseInt(request.getParameter("cid")));
        System.out.println(u.getUsername());
        request.setAttribute("user", u);
        request.getRequestDispatcher("EditUser.jsp").forward(request, response);
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
        try {
            UserDAO userDAO = new UserDAO();
            String msg = "";
            //dat bien de check validate
            boolean flag = true;
            String username = request.getParameter("userName");
            String password = request.getParameter("password");
            String repeatPassword = request.getParameter("repeatPassword");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            boolean isMale = request.getParameter("gender").equals("1");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String rolename = request.getParameter("role");
            if (userDAO.userNameIsExist(username)) {
                msg = "This username has already existed!!!";
                flag = false;
            }
            if (!password.equals(repeatPassword)) {
                msg = "Repeat password is not correct";
                flag = false;
            }
            if (userDAO.emailIsExist(email)) {
                msg = "This email has already existed!!!";
                flag = false;
            }
            
            if (flag) {
                User u = new User();
                u.setCid(Integer.parseInt(request.getParameter("cid")));
                u.setUsername(username);
                u.setPassword(password);
                u.setRole(rolename);
                u.setFullName(fullName);
                u.setAddress(address);
                u.setPhone(phone);
                u.setMale(isMale);
                u.setEmail(email);
                userDAO.updateUser(u);
                response.sendRedirect("ListUser");
            }
            else {
                request.setAttribute("flag", flag);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("EditUser.jsp?cid="+request.getParameter("cid")).forward(request, response);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
