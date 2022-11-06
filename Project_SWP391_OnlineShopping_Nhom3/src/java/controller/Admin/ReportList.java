/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

import dao.CommonDAO;
import dao.ReportDAO;
import entity.Report;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "Report", urlPatterns = {"/ReportList"})
public class ReportList extends HttpServlet {

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
            out.println("<title>Servlet Report</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Report at " + request.getContextPath() + "</h1>");
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
        try {
            ReportDAO reportDAO = new ReportDAO();
            CommonDAO commonDAO = new CommonDAO();
            ArrayList<Report> listReport;
            //String selectedStatus = (String) request.getSession().getAttribute("selectedStatus");
            int itemPerPage = 5;
            String pageCurrent = request.getParameter("page");
//            User u = (User) request.getSession().getAttribute("user");
            String roleName = (String) request.getSession().getAttribute("roleName");
            if (roleName != null && roleName.equals("Admin")) {
                if (pageCurrent != null && !pageCurrent.isEmpty()) {
                    int intPageCurrent = Integer.parseInt(pageCurrent);
//                    if (selectedStatus == null) {
//                        listShipping = shippingDAO.getShippingList("", itemPerPage, intPageCurrent, u.getCid());
//
//                    } else {
//                        listShipping = shippingDAO.getShippingList(selectedStatus, itemPerPage, intPageCurrent, u.getCid());
//                        request.setAttribute("selectedStatus", selectedStatus);
//                    }
                    listReport = reportDAO.getListReport(itemPerPage, intPageCurrent);
                    request.setAttribute("listReport", listReport);
                    int numberPage = commonDAO.getNumberPageReport(itemPerPage);
                    request.setAttribute("numberPage", numberPage);
                    request.setAttribute("pageCurrent", intPageCurrent);
                    request.getRequestDispatcher("ReportList.jsp").forward(request, response);
                } else {
                    response.sendRedirect("ReportList?page=1");
                }
            } else {
                request.setAttribute("message", "You don't have permission to access this page");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
