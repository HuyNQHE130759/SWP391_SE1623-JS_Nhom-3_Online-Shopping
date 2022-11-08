/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.CategoryDAO;
import entity.Category1;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author apc
 */
@WebServlet(name="CategoryDetail", urlPatterns={"/CategoryDetail"})
public class CategoryDetailController extends HttpServlet {
   
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
    CategoryDAO categoryDAO = new CategoryDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String raw_cid = request.getParameter("cid");
        Integer cid = (raw_cid !=null && raw_cid.length()>0)?new Integer(raw_cid):null;
        request.setAttribute("category", categoryDAO.getCategory(cid));
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("../CategoryDetail.jsp").forward(request,response);
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
        String raw_cid = request.getParameter("cid");
        String raw_cname = request.getParameter("cname").trim();
        String raw_img = request.getParameter("image").trim();
        String raw_status = request.getParameter("status").trim();
        boolean status = Boolean.parseBoolean(raw_status);
        String msg = "";
        boolean flag = true;
        Integer cid = (raw_cid !=null && raw_cid.length()>0)?new Integer(raw_cid):null;
        if (cid == null) {
            if (categoryDAO.isExisted(raw_cname)) {
                msg = "This category name has already existed!!!";
                flag = false;
            }
            if (!flag) {
                Category1 c = new Category1();
                c.setCateId(cid);
                c.setCateName(raw_cname);
                c.setImage(msg);
                c.setStatus(status);
                request.setAttribute("category", c);
                request.setAttribute("flag", flag);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("../CategoryDetail.jsp").forward(request, response);
            }
            categoryDAO.insert(raw_cname, raw_img, status);
        }else{
            categoryDAO.update(cid, raw_cname, raw_img,status);
        }
        response.sendRedirect(request.getContextPath() + "/Category/list");
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
