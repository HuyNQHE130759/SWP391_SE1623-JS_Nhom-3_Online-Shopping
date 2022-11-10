package controller.Admin;

import dao.GuaranteeDAO;
import entity.Guarantee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "EditGuarantee", urlPatterns = {"/EditGuarantee"})
public class EditGuarantee extends HttpServlet {

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
            out.println("<title>Servlet EditGuarantee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditGuarantee at " + request.getContextPath() + "</h1>");
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

        GuaranteeDAO guaranteeDAO = new GuaranteeDAO();
        Guarantee g = guaranteeDAO.getById(Integer.parseInt(request.getParameter("gid")));
        request.getSession().setAttribute("guaranteeID", request.getParameter("gid"));
        request.setAttribute("guarantee", g);
        request.getRequestDispatcher("EditGuarantee.jsp").forward(request, response);

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
            GuaranteeDAO guaranteeDAO = new GuaranteeDAO();
            String status = request.getParameter("status");
            int gid = 0;
            if (request.getSession().getAttribute("guaranteeID") != null) {
                gid = Integer.parseInt((String) request.getSession().getAttribute("guaranteeID"));
            }
            String msg = "";
            boolean flag = true;
            java.util.Date utilPickUpDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("pickUpDate"));
            java.sql.Date sqlPickUpDate = new java.sql.Date(utilPickUpDate.getTime());
            LocalDate today = LocalDate.now(ZoneId.of("America/Santarem"));
            LocalDate pickUpDateLocalDate = LocalDate.parse(request.getParameter("pickUpDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (pickUpDateLocalDate.isBefore(today)) {
                msg = "Pick up date must be greater or equals than today";
                flag = false;
            }

            if (flag) {
                guaranteeDAO.updateGuarantee(gid, status, sqlPickUpDate);
                response.sendRedirect("ListGuarantee");
            } else {
                request.setAttribute("msg", msg);
                request.setAttribute("status", request.getParameter("status"));
                request.setAttribute("pickUpDate", request.getParameter("pickUpDate"));
                request.getRequestDispatcher("/EditGuarantee.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException | ParseException e) {
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
