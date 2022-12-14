package controller.admin;

import dao.DAO;
import entity.Voucher;
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

@WebServlet(name = "AddVoucher", urlPatterns = {"/AddVoucher"})
public class AddVoucher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddVoucher at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String randomGeneratedString(int l) {
        // a list of characters to choose from in form of a string
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // creating a StringBuffer size of AlphaNumericStr
        StringBuilder s = new StringBuilder(l);
        int i;
        for (i = 0; i < l; i++) {
            //generating a random number using math.random()
            int ch = (int) (AlphaNumericStr.length() * Math.random());
            //adding Random character one by one at the end of s
            s.append(AlphaNumericStr.charAt(ch));
        }
        return s.toString();

    }

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
                //cheeck login
       if(request.getSession().getAttribute("role")==null){
            request.getSession().setAttribute("mess", "please login");
            response.sendRedirect("../Login");
            return;
       }else{
           //check role
           String role = request.getSession().getAttribute("role").toString();
           if (!role.equals("admin")) {
            request.getSession().setAttribute("mess", "you are not authorized");
            response.sendRedirect("../Login");
            return;
           }
       }
        
        request.getRequestDispatcher("/AddVoucher.jsp").forward(request, response);
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
            DAO dao = new DAO();
            String msg = "";
            boolean flag = true;
            //Get data from page
            String code = "";
            do {
                code = randomGeneratedString(10);
                request.setAttribute("code", code);
            } while (dao.voucherCodeIsExist(code));
            int discount = Integer.parseInt(request.getParameter("discount"));
            String description = request.getParameter("description");
            java.util.Date utilTimeEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("timeEnd"));
            java.sql.Date sqlTimeEnd = new java.sql.Date(utilTimeEnd.getTime());
            LocalDate today = LocalDate.now(ZoneId.of("America/Santarem"));
            LocalDate timeEndLocalDate = LocalDate.parse(request.getParameter("timeEnd"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Code: " + code);
            //Validate time end
            if (timeEndLocalDate.isBefore(today) || timeEndLocalDate.isEqual(today)) {
                msg = "Time of voucher end must be greater than today";
                flag = false;
            }

            //Add user to DB
            if (flag) {
                //If flag = true, add Voucher to db
                Voucher v = new Voucher();
                v.setCode(code);
                v.setDiscount(discount);
                v.setDescription(description);
                v.setTimeEnd(sqlTimeEnd);
                dao.addNewVoucher(v);
                response.sendRedirect("VoucherList");
            } else {
                //If flag = false, send msg to page
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/AddVoucher.jsp").forward(request, response);
            }

        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
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
