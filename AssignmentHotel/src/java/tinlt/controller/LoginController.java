/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinlt.captcha.VerifyUtils;
import tinlt.daos.RoomDAO;
import tinlt.daos.UserDAO;
import tinlt.dtos.RoomDTO;
import tinlt.dtos.RoomType;
import tinlt.dtos.UserError;

/**
 *
 * @author Ray Khum
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "loginError.jsp";
    private static final String SUCCESS = "home.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String LG = "login.jsp";

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
        String url = ERROR;
        UserError error = new UserError();
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            UserDAO dao = new UserDAO();
            RoomDAO room = new RoomDAO();
            
            List<RoomDTO> listAD = null;
            boolean check = true;
            if (email.isEmpty()) {
                check = false;
                error.setEmailError("email is not empty");
            }
            if (password.isEmpty()) {
                check = false;
                error.setPasswordError("password is not empty");
            }
            
            String user = dao.checklogin(email, password);
            String roleID = dao.checkRoleID(email, password);
            
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean valid = VerifyUtils.verify(gRecaptchaResponse);
            if (!valid) {
                url = ERROR;
            } else {
                if (user != null) {
                    if (roleID.equals("2")) {
                        session.setAttribute("LOGIN_USER", user);
                        session.setAttribute("EMAIL", email);
                        listAD = room.getListRoomAD();
                        session.setAttribute("LISTAD", listAD);
                        List<RoomType> type = room.getListRoomType();
                        session.setAttribute("TYPE_LIST", type);
                        url = ADMIN;
                    } else {
                        session.setAttribute("LOGIN_USER", user);
                        session.setAttribute("EMAIL", email);
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", error);
                    if (check) {
                        url = ERROR;
                    } else {
                        url = LG;
                    }
                }
            }
            session.setAttribute("CHECKIN", null);
            session.setAttribute("CHECKOUT", null);
            session.setAttribute("LIST", null);
            session.setAttribute("MAP", null);
            Logger.getLogger(AddToCartController.class.getName()).error("hih");
        } catch (NamingException e) {
            Logger.getLogger(LoginController.class.getName()).error(e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
