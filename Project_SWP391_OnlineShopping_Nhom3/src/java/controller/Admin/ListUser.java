package controller.admin;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import entity.User;

@WebServlet(name = "ListUser", urlPatterns = {"/ListUser"})
public class ListUser extends HttpServlet {

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
        DAO dao = new DAO();
        ArrayList<User> listUser;
        if (request.getParameter("selectRole") == null && request.getParameter("selectStatus") == null && request.getParameter("selectSort") == null) {
            listUser = dao.getListUser("", "", "cid");
        } else {
            listUser = dao.getListUser(request.getParameter("selectRole"), request.getParameter("selectStatus"), request.getParameter("selectSort"));
        }

        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("ListUser.jsp").forward(request, response);
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
