/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAO;
import dao.OrderDAO;
import entity.Bill;
import entity.BillDetail;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import utility.Support;

/**
 *
 * @author Huynq
 */
public class OrderList extends HttpServlet {

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
        try {
            OrderDAO dao = new OrderDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int cid = user.getCid();
            int page;
            if (request.getParameter("page") == null) {
                page = 1;
            } else {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<Bill> list = dao.getOrderList();
            String sort = request.getParameter("sort");
            if (sort != null) {
                request.setAttribute("sort",sort);
                if(sort.equals("1")){
                    Collections.sort(list,(o1, o2) -> {
                        return (int)(o1.getTotal() - o2.getTotal()); 
                    });
                }
                else {
                    Collections.sort(list,(o1, o2) -> {
                        return Support.ConvertStringToDate(o1.getDateCreate()).compareTo(Support.ConvertStringToDate(o2.getDateCreate())); 
                    });
                }
            }
            int numOfBills = 3;
            int numOfPages = (list.size() % 3 == 0) ? list.size() / 3 : (list.size() / 3 + 1);
            ArrayList<Bill> list_bill = Support.paging(page, numOfBills, list);
            request.setAttribute("page", page);
            request.setAttribute("list", list_bill);
            request.setAttribute("numPage", numOfPages);
            request.getRequestDispatcher("OrderList.jsp").forward(request, response);
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
