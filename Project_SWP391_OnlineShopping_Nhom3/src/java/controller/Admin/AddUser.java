/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAO;
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
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

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
        request.getRequestDispatcher("AddUser.jsp").forward(request, response);
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
                //cheeck login
       if(request.getSession().getAttribute("role")==null){
            request.getSession().setAttribute("mess", "please login");
            response.sendRedirect("../Login");
            return;
       }else{
           //check role
           String role = request.getSession().getAttribute("role").toString();
           if (!role.equals("admin")) {
            request.getSession().setAttribute("mess", "you are not authorized");
            response.sendRedirect("../Login");
            return;
           }
       }
        
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
        try {
            DAO dao = new DAO();
            String msg = "";
            boolean flag = true;
            //Get data from page
            String username = request.getParameter("userName");
            String password = request.getParameter("password");
            String repeatPassword = request.getParameter("repeatPassword");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            boolean isMale = request.getParameter("gender").equals("1");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String rolename = request.getParameter("role");
            //Validate username trung
            if (dao.userNameIsExist(username)) {
                msg = "This username has already existed!!!";
                flag = false;
            }
            // validate password
            if (!password.equals(repeatPassword)) {
                msg = "Repeat password is not correct";
                flag = false;
            }
            if (dao.emailIsExist(email)) {
                msg = "This email has already existed!!!";
                flag = false;
            }
            //Add user to DB
            if (flag) {
                //If flag = true, add User to db
                User u = new User();
                u.setUsername(username);
                u.setPassword(password);
                u.setRole(rolename);
                u.setFullName(fullName);
                u.setAddress(address);
                u.setPhone(phone);
                u.setMale(isMale);
                u.setEmail(email);
                dao.addUser(u);
                //msg = "Add user successfully!!!"; 
                response.sendRedirect("ListUser");
            }
            else {
                //If flag = false, send msg to page
                request.setAttribute("msg", msg);
                response.sendRedirect("AddUser");
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
