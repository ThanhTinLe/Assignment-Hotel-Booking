/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tinlt.daos.UserDAO;
import tinlt.dtos.UserDTO;
import tinlt.dtos.UserError;

/**
 *
 * @author Ray Khum
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.jsp";

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
        try {
            UserDAO dao = new UserDAO();
            String fullname = request.getParameter("txtfullnamec");
            String email = request.getParameter("txtemailc");
            String password = request.getParameter("txtpasswordc");
            String confirm = request.getParameter("txtconfirm");
            UserError error = new UserError();
            boolean check = true;
            if (fullname.isEmpty()) {
                check = false;
                error.setFullnameError("fullname is not empty");
            }
            if (email.isEmpty()) {
                check = false;
                error.setEmailError("email is not empty");
            }
            if (dao.checkEmail(email) != false) {
                check = false;
                error.setEmailError("email da ton tai");
            }
            if (password.isEmpty()) {
                check = false;
                error.setPasswordError("password is not empty");
            }
            if (confirm.isEmpty()) {
                check = false;
                error.setConfirmError("confirm is not empty ");
            }
            if (!confirm.equals(password)) {
                check = false;
                error.setConfirmError("2 password khong giong nhau");
            }
            if (check) {
                UserDTO dto = new UserDTO(email, fullname, password, "1");
                dao.createUser(dto);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
            Logger.getLogger(CreateController.class.getName()).error(e.getMessage());
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
