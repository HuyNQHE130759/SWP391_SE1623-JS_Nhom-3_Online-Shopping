package controller.Admin;

import dao.CommonDAO;
import dao.GuaranteeDAO;
import entity.Guarantee;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "ListGuarantee", urlPatterns = {"/ListGuarantee"})
public class ListGuarantee extends HttpServlet {

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
            out.println("<title>Servlet ListGuarantee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListGuarantee at " + request.getContextPath() + "</h1>");
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
            GuaranteeDAO guaranteeDAO = new GuaranteeDAO();
            ArrayList<Guarantee> list;
            CommonDAO commonDAO = new CommonDAO();
            String roleName = (String) request.getSession().getAttribute("roleName");
            User user = (User) request.getSession().getAttribute("user");
            int itemPerPage = 5;
            String pageCurrent = request.getParameter("page");
            if (roleName != null) {
                if (pageCurrent != null && !pageCurrent.isEmpty()) {
                    int intPageCurrent = Integer.parseInt(pageCurrent);
                    if (roleName.equalsIgnoreCase("admin")) {
                        list = guaranteeDAO.getListGuarantee(itemPerPage, intPageCurrent, null, (String) request.getSession().getAttribute("status"));
                    } else {
                        list = guaranteeDAO.getListGuarantee(itemPerPage, intPageCurrent, user.getUsername(), (String)request.getSession().getAttribute("status"));
                    }
                    request.setAttribute("listGuarantee", list);
                    int numberPage = commonDAO.getNumberPage(itemPerPage, "Guaranteee");
                    request.setAttribute("numberPage", numberPage);
                    request.setAttribute("pageCurrent", intPageCurrent);
                    request.getRequestDispatcher("ListGuarantee.jsp").forward(request, response);
                } else {
                    response.sendRedirect("ListGuarantee?page=1");
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
        String status = request.getParameter("status");
        request.getSession().setAttribute("status", status);
        response.sendRedirect("ListGuarantee?page=1");
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
