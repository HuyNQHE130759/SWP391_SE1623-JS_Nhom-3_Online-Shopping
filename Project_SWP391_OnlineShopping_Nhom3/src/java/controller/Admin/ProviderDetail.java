/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Admin;

import dao.ProviderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author apc
 */
public class ProviderDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ProviderDAO providerDAO = new ProviderDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String raw_pid = request.getParameter("pid");
        Integer pid = (raw_pid !=null && raw_pid.length()>0)?new Integer(raw_pid):null;
        request.setAttribute("category", providerDAO.getProvider(pid));
        request.setAttribute("pid", pid);
        request.getRequestDispatcher("../ProviderDetail.jsp").forward(request,response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String raw_pid = request.getParameter("pid");
        String raw_pname = request.getParameter("pname");
        String raw_email = request.getParameter("email");
        String raw_address = request.getParameter("address");
        String raw_status = request.getParameter("status");
        boolean status = Boolean.parseBoolean(raw_status);
        Integer pid = (raw_pid !=null && raw_pid.length()>0)?new Integer(raw_pid):null;
        if (pid != null) {
            providerDAO.insert(raw_pname, raw_email, raw_address, status);
        }else{
            providerDAO.update(pid, raw_pname, raw_email, raw_address, status);
        }
        response.sendRedirect(request.getContextPath() + "/Provider/list");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
