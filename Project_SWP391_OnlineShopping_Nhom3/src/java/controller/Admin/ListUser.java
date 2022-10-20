package controller.Admin;

import dao.CommonDAO;
import dao.UserDAO;
import java.io.IOException;
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
        UserDAO userDAO = new UserDAO();
        CommonDAO commonDAO = new CommonDAO();
        ArrayList<User> listUser;
        String selectedRole = (String) request.getSession().getAttribute("selectedRole");
        String selectedStatus = (String) request.getSession().getAttribute("selectedStatus");
        String selectedSort = (String) request.getSession().getAttribute("selectedSort");
        int itemPerPage = 5;
        String pageCurrent = request.getParameter("page");
        if (pageCurrent != null && !pageCurrent.isEmpty()) {
            int intPageCurrent = Integer.parseInt(pageCurrent);
            if (selectedRole == null && selectedStatus == null && selectedSort == null) {
                listUser = userDAO.getListUser("", "", "cid", itemPerPage, intPageCurrent);

            } else {
                listUser = userDAO.getListUser(selectedRole, selectedStatus, selectedSort, itemPerPage, intPageCurrent);
                request.setAttribute("selectedRole", selectedRole);
                request.setAttribute("selectedStatus", selectedStatus);
                request.setAttribute("selectedSort", selectedSort);
            }
            request.setAttribute("listUser", listUser);
            // get number page to paging
            int numberPage = commonDAO.getNumberPage(itemPerPage, "[User]");
            request.setAttribute("numberPage", numberPage);
            // get page current
            request.setAttribute("pageCurrent", intPageCurrent);
        }
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
        String selectedRole = request.getParameter("selectRole");
        String selectedStatus = request.getParameter("selectStatus");
        String selectedSort = request.getParameter("selectSort");
        request.getSession().setAttribute("selectedRole", selectedRole);
        request.getSession().setAttribute("selectedStatus", selectedStatus);
        request.getSession().setAttribute("selectedSort", selectedSort);
        response.sendRedirect("ListUser?page=1");
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
