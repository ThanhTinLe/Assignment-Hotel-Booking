/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Ray Khum
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String LOGIN = "LoginController";
    private static final String LOGIN_PAGE = "LogoutController";
    private static final String CREATE = "CreateController";
    private static final String CREATE_PAGE = "create.jsp";
    private static final String CHECKIN = "HomeController";
    private static final String DETAIL = "DetailController";
    private static final String HOME = "home.jsp";
    private static final String CART = "AddToCartController";
    private static final String CART_PAGE = "cart.jsp";
    private static final String DELETE = "CartController";
    private static final String BOOKING = "BookingController";
    private static final String UPDATE_AD = "UpdateADController";
    private static final String UPDATE_PAGE = "UpdateRommController";
    private static final String DELETE_AD = "DeleteADController";
    private static final String AD_PAGE = "admin.jsp";
    private static final String LOGIN_GMAIL = "LoginGmailController";

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
        String path = "D:\\code\\code_java\\java_web\\AssignmentHotel\\log4j.log";
        try {
            Properties properties = new Properties();
            properties.setProperty("log4j.rootLogger", "TRACE,stdout,MyFile");
            properties.setProperty("log4j.rootCategory", "TRACE");
            properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
            properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
            properties.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
            properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
            properties.setProperty("log4j.appender.MyFile.File", path);
            properties.setProperty("log4j.appender.MyFile.MaxFileSize", "1MB");
            properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
            properties.setProperty("log4j.appender.MyFile.layout", "org.apache.log4j.PatternLayout");
            properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern", "%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
            PropertyConfigurator.configure(properties);

            String action = request.getParameter("btnAction");
            if (action.equals("Login")) {
                url = LOGIN;
            }
            if (action.equals("Login Page")) {
                url = LOGIN_PAGE;
            }
            if (action.equals("Create")) {
                url = CREATE;
            }
            if (action.equals("Create Page")) {
                url = CREATE_PAGE;
            }
            if (action.equals("Check In")) {
                url = CHECKIN;
            }
            if (action.equals("Detail")) {
                url = DETAIL;
            }
            if (action.equals("Home")) {
                url = HOME;
            }
            if (action.equals("Add To Cart")) {
                url = CART;
            }
            if (action.equals("View Cart")) {
                url = CART_PAGE;
            }
            if (action.equals("Add More")) {
                url = HOME;
            }
            if (action.equals("Delete Room Cart")) {
                url = DELETE;
            }
            if (action.equals("Booking Now")) {
                url = BOOKING;
            }
            if (action.equals("Delete Room")) {
                url = DELETE_AD;
            }
            if (action.equals("Update Room")) {
                url = UPDATE_PAGE;
            }
            if (action.equals("Update Page")) {
                url = UPDATE_AD;
            }
            if (action.equals("Return")) {
                url = AD_PAGE;
            }
            if (action.equals("Gmail")) {
                url = LOGIN_GMAIL;
            }
        } catch (Exception e) {
            Logger.getLogger(MainController.class.getName()).error(e.getMessage());
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
