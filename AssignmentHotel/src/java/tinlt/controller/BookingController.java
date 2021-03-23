/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tinlt.daos.GmailDAO;
import tinlt.daos.RoomDAO;
import tinlt.dtos.RoomDTO;

/**
 *
 * @author Ray Khum
 */
public class BookingController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
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
            HttpSession session = request.getSession();
            Map<String, RoomDTO> cart = (Map<String, RoomDTO>) session.getAttribute("MAP");
            RoomDAO room = new RoomDAO();

            Date date = new Date();
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
            String now = Format.format(date);
            String checkIn = (String) session.getAttribute("CHECKIN");
            String checkOut = (String) session.getAttribute("CHECKOUT");

            int quantity = cart.keySet().size();
            String email = (String) session.getAttribute("EMAIL");
            String orderID = room.countOrder();
            float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
            List<RoomDTO> list = new RoomDAO().getListRoom(checkIn, checkOut);

            int index = 0;
            if (cart != null) {
                for (String key : cart.keySet()) {
                    for (RoomDTO roomDTO : list) {
                        if (key.equals(roomDTO.getRoomID())) {
                            index++;
                        }
                    }
                }
                if (index == cart.keySet().size()) {
                    if (room.addToOrder(orderID, email, totalPrice, now, checkIn, checkOut, quantity) == true) {
                        for (String key : cart.keySet()) {
                            if (room.addToOrderDetail(orderID, key, cart.get(key).getPrice()) == true) {
                                session.setAttribute("CHECKIN", null);
                                session.setAttribute("CHECKOUT", null);
                                session.setAttribute("LIST", null);
                                url = SUCCESS;
                                GmailDAO.sendMail(email, "Booking success", "Booking success! thanks for booking! see you late");
                            }
                        }
                    }
                    session.setAttribute("MAP", null);
                    cart = new HashMap<>();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(BookingController.class.getName()).error(e.getMessage());
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
