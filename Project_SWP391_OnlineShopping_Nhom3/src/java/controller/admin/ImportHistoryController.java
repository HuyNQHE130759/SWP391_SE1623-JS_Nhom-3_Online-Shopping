/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.ImportDAO;
import dao.ImportDAOInterface;
import dao.ProductDAO;
import dao.ProviderDAO;
import dao.ProviderDAOInterface;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author apc
 */
public class ImportHistoryController extends HttpServlet {
   
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
    ProviderDAOInterface providerDAO = new ProviderDAO();
    ImportDAOInterface importDAO = new ImportDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page == null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        int count = importDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("imports", importDAO.getAllImport(pageindex, pagesize));
        request.setAttribute("providers", providerDAO.getAllProvider());
        request.getRequestDispatcher("../ImportHistory.jsp").forward(request, response);
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
        String raw_provider = request.getParameter("provider");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        String raw_search = request.getParameter("search");
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("PAGE_SIZE"));
        String raw_page = request.getParameter("page");
        if(raw_page ==null)
            raw_page = "1";
        int pageindex = Integer.parseInt(raw_page);
        Integer provider = (raw_provider !=null && raw_provider.length()>0)?new Integer(raw_provider):null;
        String search = (raw_search !=null && raw_search.length()>0)?raw_search:null;
        Date from = Date.valueOf(raw_from);
        Date to = Date.valueOf(raw_to);
        ProductDAO productDAO = new ProductDAO();
        int count = productDAO.count();
        int totalpage = (count%pagesize ==0)?count/pagesize:count/pagesize + 1;
        request.setAttribute("sort", sort);
        request.setAttribute("provider", raw_provider);
        request.setAttribute("from", raw_from);
        request.setAttribute("to", raw_to);
        request.setAttribute("search", raw_search);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("providers", providerDAO.getAllProvider());
        request.setAttribute("products", importDAO.getAllImport(sort, provider, from, to, search, pageindex, pagesize));
        request.getRequestDispatcher("../ImportHistory.jsp").forward(request, response);
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
