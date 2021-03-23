/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tinlt.dtos.RoomDTO;
import tinlt.dtos.RoomType;

/**
 *
 * @author Ray Khum
 */
public class RoomDAO {

    public List<RoomDTO> getListRoom(String checkin, String checkout) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        RoomDTO room = null;
        ResultSet rs = null;
        List<RoomDTO> list = new ArrayList<>();
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT roomId, roomName, price, desciption, maxPeople, urlImage, typeRoomId "
                        + "FROM tblRooms "
                        + "WHERE roomId not in (SELECT tblOderDetail.roomId "
                        + "                     FROM tblOders , tblOderDetail  "
                        + "			    WHERE tblOders.orderId = tblOderDetail.orderId "
                        + "                         and checkIn between ? and ? or checkOut between ? and ? "
                        + "                         or checkIn between ? and ? and checkOut between ? and ? "
                        + "                         or checkIn < ? and checkOut >?  ) ";
                pst = cn.prepareStatement(url);
                pst.setString(1, checkin);
                pst.setString(2, checkout);
                pst.setString(3, checkin);
                pst.setString(4, checkout);
                pst.setString(5, checkin);
                pst.setString(6, checkout);
                pst.setString(7, checkin);
                pst.setString(8, checkout);
                pst.setString(9, checkin);
                pst.setString(10, checkout);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomId");
                    String roomName = rs.getString("roomName");
                    float price = rs.getFloat("price");
                    String desciption = rs.getString("desciption");
                    int max = rs.getInt("maxPeople");
                    String imageURL = rs.getString("urlImage");
                    String typeRoomID = rs.getString("typeRoomId");
                    room = new RoomDTO(roomID, roomName, price, desciption, max, imageURL, typeRoomID);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(room);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<RoomType> getListRoomType() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        RoomType type = null;
        ResultSet rs = null;
        List<RoomType> list = new ArrayList<>();
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT typeRoomId, typeName FROM tblTypeRoom";
                pst = cn.prepareStatement(url);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String typeID = rs.getString("typeRoomId");
                    String typeName = rs.getString("typeName");

                    type = new RoomType(typeID, typeName);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(type);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public boolean addToOrder(String orderID, String email, float totalPrice, String date, String checkIn, String checkOut, int quantity) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "INSERT INTO tblOders (orderId, email, totalPrice, date, checkIn, checkOut, quantity )\n"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                pst = cn.prepareStatement(url);
                pst.setString(1, orderID);
                pst.setString(2, email);
                pst.setFloat(3, totalPrice);
                pst.setString(4, date);
                pst.setString(5, checkIn);
                pst.setString(6, checkOut);
                pst.setInt(7, quantity);
                pst.executeUpdate();
                check = true;
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public boolean addToOrderDetail(String orderId, String roomId, float price) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "INSERT INTO tblOderDetail (orderId, roomId, price )\n"
                        + " VALUES (?, ?, ?);";
                pst = cn.prepareStatement(url);
                pst.setString(1, orderId);
                pst.setString(2, roomId);
                pst.setFloat(3, price);
                pst.executeUpdate();
                check = true;
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public String countOrder() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String orderID = "o1";
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            String url = "select orderid from tblOders where orderID >= all(select orderid from tblOders) ";
            pst = cn.prepareStatement(url);
            rs = pst.executeQuery();
            if (rs.next()) {
                orderID = rs.getString(1);
                int num = Integer.parseInt(orderID.substring(1));
                orderID = orderID.substring(0, 1) + ++num;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return orderID;

    }

    public List<RoomDTO> getListRoomAD() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        RoomDTO room = null;
        ResultSet rs = null;
        List<RoomDTO> list = new ArrayList<>();
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT roomId, roomName, price, desciption, maxPeople, urlImage, typeRoomId "
                        + "FROM tblRooms ";
                pst = cn.prepareStatement(url);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomId");
                    String roomName = rs.getString("roomName");
                    float price = rs.getFloat("price");
                    String desciption = rs.getString("desciption");
                    int max = rs.getInt("maxPeople");
                    String imageURL = rs.getString("urlImage");
                    String typeRoomID = rs.getString("typeRoomId");
                    room = new RoomDTO(roomID, roomName, price, desciption, max, imageURL, typeRoomID);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(room);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public boolean DeleteRoom(String roomID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "DELETE FROM tblRooms WHERE roomId=?;";
                pst = cn.prepareStatement(url);
                pst.setString(1, roomID);
                pst.executeUpdate();
                check = true;
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public boolean UpdateRoom(String roomID, String roomName, float price, String desciption, int maxPeople, String urlImage, String typeRoomId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "UPDATE tblRooms SET roomName=?, price=?, desciption=?, maxPeople=?, urlImage=?, typeRoomId=? WHERE roomId=?;";
                pst = cn.prepareStatement(url);
                pst.setString(1, roomName);
                pst.setFloat(2, price);
                pst.setString(3, desciption);
                pst.setInt(4, maxPeople);
                pst.setString(5, urlImage);
                pst.setString(6, typeRoomId);
                pst.setString(7, roomID);
                pst.executeUpdate();
                check = true;
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }
}
