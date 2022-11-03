/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CommonDAO;
import dao.ShippingDAO;
import entity.Shipping;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "ShippingList", urlPatterns = {"/ShippingList"})
public class ShippingList extends HttpServlet {

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
        try {
            ShippingDAO shippingDAO = new ShippingDAO();
            CommonDAO commonDAO = new CommonDAO();
            ArrayList<Shipping> listShipping;
            String selectedStatus = request.getParameter("selectStatus");
            int itemPerPage = 5;
            String pageCurrent = request.getParameter("page");
            if (pageCurrent != null && !pageCurrent.isEmpty()) {
                int intPageCurrent = Integer.parseInt(pageCurrent);
                if (selectedStatus == null) {
                    listShipping = shippingDAO.getShippingList("", itemPerPage, intPageCurrent);
                } else {
                    listShipping = shippingDAO.getShippingList(selectedStatus, itemPerPage, intPageCurrent);
                }
                request.setAttribute("listShipping", listShipping);
                // get number page to paging
                int numberPage = commonDAO.getNumberPageShipping(itemPerPage);
                request.setAttribute("numberPage", numberPage);
                // get page current
                request.setAttribute("pageCurrent", intPageCurrent);
            }
            request.getRequestDispatcher("ShippingList.jsp").forward(request, response);
        } catch (Exception e) {
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
            ShippingDAO shippingDAO = new ShippingDAO();
            CommonDAO commonDAO = new CommonDAO();
            ArrayList<Shipping> listShipping;
            String selectedStatus = (String) request.getSession().getAttribute("selectedStatus");
            int itemPerPage = 5;
            String pageCurrent = request.getParameter("page");
            if (pageCurrent != null && !pageCurrent.isEmpty()) {
                int intPageCurrent = Integer.parseInt(pageCurrent);
                if (selectedStatus == null) {
                    listShipping = shippingDAO.getShippingList("", itemPerPage, intPageCurrent);

                } else {
                    listShipping = shippingDAO.getShippingList(selectedStatus, itemPerPage, intPageCurrent);
                    request.setAttribute("selectedStatus", selectedStatus);
                }
                request.setAttribute("listShipping", listShipping);
                int numberPage = commonDAO.getNumberPageShipping(itemPerPage);
                request.setAttribute("numberPage", numberPage);
                request.setAttribute("pageCurrent", intPageCurrent);
            }
            request.getRequestDispatcher("ShippingList.jsp").forward(request, response);
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
        String selectedStatus = request.getParameter("selectStatus");
        request.getSession().setAttribute("selectedStatus", selectedStatus);
        response.sendRedirect("ShippingList?page=1");
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
