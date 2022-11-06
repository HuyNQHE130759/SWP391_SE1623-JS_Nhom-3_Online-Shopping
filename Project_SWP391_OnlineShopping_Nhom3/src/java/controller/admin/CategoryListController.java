/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.CategoryDAO;
import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author apc
 */
public class CategoryListController extends HttpServlet {
   
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
        CategoryDAO categoryDAO = new CategoryDAO();
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page == null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        int count = categoryDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("categories", categoryDAO.getAllCategory(pageindex, pagesize));
        request.getRequestDispatcher("../CategoryList.jsp").forward(request, response);
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
        String sort = request.getParameter("sort").trim();
        String raw_status = request.getParameter("status").trim();
        String raw_search = request.getParameter("search").trim();
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page ==null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        Boolean status = (raw_status !=null && raw_status.length()>0 && !raw_status.equals("-1"))?(raw_status.equals("0")?false:true):null;
        String search = (raw_search !=null && raw_search.length()>0)?raw_search:null;
        CategoryDAO categoryDAO = new CategoryDAO();
        DAO dao = new DAO();
        int count = categoryDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("sort", sort);
        request.setAttribute("status", raw_status);
        request.setAttribute("search", raw_search);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("categories", categoryDAO.getAllCategory(sort, status, search, pageindex, pagesize));
        request.getRequestDispatcher("../CategoryList.jsp").forward(request, response);
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
