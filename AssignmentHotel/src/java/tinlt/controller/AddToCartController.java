/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AddToCartController extends HttpServlet {

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
            String roomID1 = null;
            String roomName = null;
            float price = 0;
            String description = null;
            int maxPeople = 0;
            String photoLink = null;
            String typeRoomID = null;
            String roomID = request.getParameter("roomID");
            String checkIn = request.getParameter("txtcheckin");
            String checkOut = request.getParameter("txtcheckout");
            HttpSession session = request.getSession();
            Map<String, RoomDTO> map = (Map<String, RoomDTO>) session.getAttribute("MAP");
            RoomDAO room = new RoomDAO();
            List<RoomDTO> list = room.getListRoom(checkIn, checkOut);
            List<RoomDTO> cart = new ArrayList<>();
            RoomDTO roomdto = null;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRoomID().equals(roomID)) {
                    roomID1 = list.get(i).getRoomID();
                    roomName = list.get(i).getRoomName();
                    price = list.get(i).getPrice();
                    description = list.get(i).getDescription();
                    maxPeople = list.get(i).getMaxPeople();
                    photoLink = list.get(i).getPhotoLink();
                    typeRoomID = list.get(i).getTypeRoomID();
                    request.setAttribute("OK", list.get(i).getRoomName() + " added");
                }
            }
            roomdto = new RoomDTO(roomID1, roomName, price, description, maxPeople, photoLink, typeRoomID);
            if (roomdto != null) {
                //cart.add(roomdto);
                if (map == null) {
                    map = new HashMap<>();
                } else {
                }
                map.put(roomID1, roomdto);
                session.setAttribute("MAP", map);
            }
            url = SUCCESS;
        } catch (Exception e) {
            Logger.getLogger(AddToCartController.class.getName()).error(e.getMessage());
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
