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
import javax.naming.NamingException;
import tinlt.dtos.UserDTO;

/**
 *
 * @author Ray Khum
 */
public class UserDAO {

    public String checklogin(String email, String password) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String result = null;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "select fullname "
                        + "from tblUsers "
                        + "where email=? and password=? ";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("fullname");
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
        return result;
    }

    public boolean createUser(UserDTO user) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "INSERT INTO tblUsers(fullname,email,Password,roleid)VALUES (?, ?, ?, 1)";
                pst = cn.prepareStatement(url);
                pst.setString(1, user.getFullname());
                pst.setString(2, user.getEmail());
                pst.setString(3, user.getPassword());
                pst.executeQuery();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return true;
    }

    public boolean checkEmail(String email) throws SQLException, NamingException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = tinlt.utils.DBUtils.getConnection();

            if (cn != null) {
                String url = "SELECT email\n"
                        + "FROM tblUsers\n"
                        + "WHERE email=?";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public String checkRoleID(String email, String password) throws SQLException, NamingException {
        String result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = tinlt.utils.DBUtils.getConnection();
            if (cn != null) {
                String url = "select roleID "
                        + "from tblUsers "
                        + "where email=? and password=? ";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("roleID");
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
        return result;
    }
}
