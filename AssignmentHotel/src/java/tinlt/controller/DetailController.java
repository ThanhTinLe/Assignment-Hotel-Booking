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
import org.apache.log4j.Logger;
import tinlt.daos.RoomDAO;
import tinlt.dtos.RoomDTO;
import tinlt.dtos.RoomType;

/**
 *
 * @author Ray Khum
 */
public class DetailController extends HttpServlet {

    private static final String ERROR = "search.jsp";
    private static final String SUCCESS = "detail.jsp";

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
            tinlt.daos.RoomDAO room = new RoomDAO();
            String checkIn = request.getParameter("txtcheckin");
            String checkOut = request.getParameter("txtcheckout");
            List<RoomDTO> list = room.getListRoom(checkIn, checkOut);
            List<RoomType> type = room.getListRoomType();
            String roomID = request.getParameter("roomID");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRoomID().equals(roomID)) {
                    request.setAttribute("DETAIL", list.get(i));
                    for (int j = 0; j < type.size(); j++) {
                        if (list.get(i).getTypeRoomID().equals(type.get(j).getTypeID())) {
                            request.setAttribute("TYPE", type.get(j).getTypeName());
                        }
                    }
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            Logger.getLogger(DetailController.class.getName()).error(e.getMessage());
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
