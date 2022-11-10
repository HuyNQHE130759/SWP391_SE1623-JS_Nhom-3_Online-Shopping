package controller;

import dao.GuaranteeDAO;
import entity.Guarantee;
import entity.User;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SendRequestGuarantee", urlPatterns = {"/SendRequestGuarantee"})
@MultipartConfig(maxFileSize = 16177215)
public class SendRequestGuarantee extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String UPLOAD_DIR = "uploadedFiles";

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
            out.println("<title>Servlet SendRequestGuarantee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendRequestGuarantee at " + request.getContextPath() + "</h1>");
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
            String roleName = (String) request.getSession().getAttribute("roleName");
            if (roleName != null && roleName.equalsIgnoreCase("user")) {
                GuaranteeDAO guaranteeDAO = new GuaranteeDAO();
                ArrayList<Product> listProduct = guaranteeDAO.getDropdownProduct();
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("SendRequestGuarantee.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "You don't have permission to access this page");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | SQLException e) {
            request.setAttribute("message", "You don't have permission to access this page");
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
        try {
            String roleName = (String) request.getSession().getAttribute("roleName");
            User user = (User) request.getSession().getAttribute("user");
            if (roleName != null && roleName.equalsIgnoreCase("user")) {
                String product = request.getParameter("product");
                String description = request.getParameter("description");
                Guarantee g = new Guarantee();
                g.setProduct(product);
                g.setDescription(description);
                g.setOwner(user.getUsername());
                GuaranteeDAO guaranteeDAO = new GuaranteeDAO();
                guaranteeDAO.createRequestGuarantee(g);
                request.setAttribute("successMsg", "Your request for guarantee has been submitted. We will contact you after finish");
                request.getRequestDispatcher("Success.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "You don't have permission to access this page");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | SQLException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
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
