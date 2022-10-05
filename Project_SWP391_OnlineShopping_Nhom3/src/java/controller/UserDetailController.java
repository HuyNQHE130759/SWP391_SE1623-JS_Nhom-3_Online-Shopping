/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RoleDAO;
import dao.UserDAO;
import entity.Role;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.User;

/**
 *
 * @author apc
 */
public class UserDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String raw_uId = request.getParameter("uId");
        int uId = (raw_uId != null && raw_uId.length() > 0)?Integer.parseInt(raw_uId):-1;
        int flag = uId ==-1?0:1;
        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();
        request.setAttribute("roles", roleDAO.getAllRoles());
        request.setAttribute("flag", flag);
        request.setAttribute("user", userDAO.getUserbyId(uId));
        request.getRequestDispatcher("../view/user/detail.jsp").forward(request, response);
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
        String raw_uId = request.getParameter("uId");
        String raw_email = request.getParameter("email");
        String raw_fullname = request.getParameter("fullname");
        String raw_password = request.getParameter("password");
        String raw_gender = request.getParameter("gender");
        String raw_phone = request.getParameter("phone");
        String raw_address = request.getParameter("address");
        String raw_status = request.getParameter("status");
        String raw_role = request.getParameter("role");
        UserDAO userDAO = new UserDAO();
        boolean status = (raw_status.equals("0"))?false:true;
        Role role = new Role();
        role.setRoleId(Integer.parseInt(raw_role));
        int uId = raw_uId != null && raw_uId.length() > 0 && !raw_uId.equals("-1") ? Integer.parseInt(raw_uId) : -1;
        boolean gender = raw_gender.equals("Male")?true:false;
        User u = new User();
        if (uId != -1) {
            userDAO.update(u);
        }else{
            userDAO.insert(u);
        }
        request.getRequestDispatcher("../view/user/detail.jsp").forward(request, response);
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
