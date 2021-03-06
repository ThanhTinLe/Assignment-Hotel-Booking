/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinlt.daos.RoomDAO;
import tinlt.dtos.RoomDTO;

/**
 *
 * @author Ray Khum
 */
public class HomeController extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "home.jsp";

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
            Date date = new Date();
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");

            String checkin = request.getParameter("txtcheckin");
            String checkout = request.getParameter("txtcheckout");
            HttpSession session = request.getSession();

            RoomDAO room = new RoomDAO();
            List<RoomDTO> list = room.getListRoom(checkin, checkout);
            boolean check = true;

            if (checkin.isEmpty() || checkout.isEmpty()) {
                if (checkin.isEmpty()) {
                    request.setAttribute("CHECKINERROR", "please enter your checkin day");
                    session.setAttribute("CHECKOUT", checkout);
                    check = false;
                }
                if (checkout.isEmpty()) {
                    request.setAttribute("CHECKOUTERROR", "please enter your checkout day");
                    session.setAttribute("CHECKIN", checkin);
                    check = false;
                }
            } else {
                if (checkin.compareTo(Format.format(date)) < 0) {
                    request.setAttribute("CHECKINERROR", "check in day" + ">=" + Format.format(date));
                    check = false;
                }
                if (checkout.compareTo(checkin) <= 0) {
                    request.setAttribute("CHECKOUTERROR", "check out day" + ">" + "check in day");
                    check = false;
                }
            }
            if (check) {
                session.setAttribute("LIST", list);
            }
            session.setAttribute("CHECKOUT", checkout);
            session.setAttribute("CHECKIN", checkin);
            url = SUCCESS;
        } catch (Exception e) {
            Logger.getLogger(HomeController.class.getName()).error(e.getMessage());
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
