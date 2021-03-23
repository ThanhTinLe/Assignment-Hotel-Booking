/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
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
public class UpdateRommController extends HttpServlet {

    private static final String ERROR = "updateRoom.jsp";
    private static final String SUCCESS = "admin.jsp";

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
            String roomID = request.getParameter("updateID");
            String roomName = request.getParameter("updateName");
            float price = Float.parseFloat(request.getParameter("updatePrice"));
            String description = request.getParameter("updateDescription");
            int maxPeople = Integer.parseInt(request.getParameter("updateMaxPeople"));
            String photoLink = request.getParameter("updatePhotoRoom");
            String typeRoomID = request.getParameter("updateTypeRoomID");

            HttpSession session = request.getSession();
            RoomDAO room = new RoomDAO();
            List<RoomDTO> listAD = null;
            boolean check = true;

            if (roomName.isEmpty()) {
                check = false;
            }
            if (price <= 0) {
                check = false;
            }
            if (description.isEmpty()) {
                check = false;
            }
            if (maxPeople < 0) {
                check = false;
            }
            if (photoLink.isEmpty()) {
                check = false;
            }
            if (typeRoomID.equals("0")) {
                check = false;
            }
            if (check) {
                RoomDAO roomDAO = new RoomDAO();
                if (roomDAO.UpdateRoom(roomID, roomName, price, description, maxPeople, photoLink, typeRoomID)) {
                    listAD = room.getListRoomAD();
                    session.setAttribute("LISTAD", listAD);
                    url = SUCCESS;
                }
            } else {
                url = ERROR;
            }
        } catch (Exception e) {
            Logger.getLogger(UpdateRommController.class.getName()).error(e.getMessage());
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
