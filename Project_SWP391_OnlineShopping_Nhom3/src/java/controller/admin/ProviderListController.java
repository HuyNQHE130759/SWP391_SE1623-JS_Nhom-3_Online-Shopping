/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

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
public class ProviderListController extends HttpServlet {
   
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
        ProviderDAO providerDAO = new ProviderDAO();
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page == null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        int count = providerDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("providers", providerDAO.getAllProvider(pageindex, pagesize));
        request.getRequestDispatcher("../ProviderList.jsp").forward(request, response);
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
        String sort = request.getParameter("sort");
        String raw_status = request.getParameter("status");
        String raw_search = request.getParameter("search");
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page ==null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        Boolean status = (raw_status !=null && raw_status.length()>0 && !raw_status.equals("-1"))?(raw_status.equals("0")?false:true):null;
        String search = (raw_search !=null && raw_search.length()>0)?raw_search:null;
        ProviderDAO providerDAO = new ProviderDAO();
        int count = providerDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("sort", sort);
        request.setAttribute("status", raw_status);
        request.setAttribute("search", raw_search);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("providers", providerDAO.getAllProvider(sort, status, search, pageindex, pagesize));
        request.getRequestDispatcher("../ProviderList.jsp").forward(request, response);
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
