/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CategoryDAO;
import dao.CategoryDAOInterface;
import dao.ProductDAO;
import dao.ProductDAOInterface;
import dao.ProviderDAO;
import dao.ProviderDAOInterface;
import entity.Category1;
import entity.Product1;
import entity.Provider;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author apc
 */
public class AdminProductDetailController extends HttpServlet {

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
    CategoryDAOInterface categoryDAO = new CategoryDAO();
    ProductDAOInterface productDAO = new ProductDAO();
    ProviderDAOInterface providerDAO = new ProviderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //cheeck login
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

        String raw_pid = request.getParameter("pid");
        Integer pid = (raw_pid != null && raw_pid.length() > 0) ? new Integer(raw_pid) : null;
        request.setAttribute("categoryList", categoryDAO.getAllCategory());
        request.setAttribute("providers", providerDAO.getAllProvider());
        request.setAttribute("product", productDAO.getProductById(pid));
        request.setAttribute("pid", pid);
        request.getRequestDispatcher("../AdminProductDetail.jsp").forward(request, response);
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
        String raw_pid = request.getParameter("pid");
        String raw_pname = request.getParameter("pname").trim();
        String raw_price = request.getParameter("price").trim();
        String raw_img = request.getParameter("image").trim();
        String raw_description = request.getParameter("description").trim();
        String raw_cid = request.getParameter("category").trim();
        String raw_pvid = request.getParameter("provider").trim();
        String raw_status = request.getParameter("status").trim();
        String msg = "";
        boolean flag = true;
        boolean status = Boolean.parseBoolean(raw_status);
        float price = Float.parseFloat(raw_price);
        int cid = Integer.parseInt(raw_cid);
        int pvid = Integer.parseInt(raw_pvid);
        Integer pid = (raw_pid != null && raw_pid.length() > 0) ? new Integer(raw_pid) : null;
        if (pid == null) {
            if (productDAO.isExisted(raw_pname)) {
                msg = "This product name has already existed!!!";
                flag = false;
            }
            if (!flag) {
                Product1 pd = new Product1();
                pd.setPid(pid);
                pd.setDescription(raw_description);
                pd.setImage(raw_img);
                pd.setPname(raw_pname);
                pd.setStatus(status);
                Category1 c = new Category1();
                c.setCateId(cid);
                pd.setCategory(c);
                Provider pv = new Provider();
                pv.setProvider_id(pvid);
                pd.setProvider(pv);
                pd.setPrice(price);
                request.setAttribute("categoryList", categoryDAO.getAllCategory());
                request.setAttribute("providers", providerDAO.getAllProvider());
                request.setAttribute("product", pd);
                request.setAttribute("flag", flag);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("../AdminProductDetail.jsp").forward(request, response);
            }
            productDAO.insert(raw_pname, raw_img, price, raw_description, status, cid, pvid);

        } else {
            productDAO.update(pid, raw_pname, raw_img, price, raw_description, status, cid, pvid);
        }
        response.sendRedirect(request.getContextPath() + "/AdminProduct/list");
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
