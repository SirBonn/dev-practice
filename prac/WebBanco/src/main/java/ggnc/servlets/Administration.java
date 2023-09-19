/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets;

import ggnc.servlets.services.AdministrationServices;
import ggnc.webbanco.domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 *
 * @author sirbon
 */
@WebServlet(name = "Administration", urlPatterns = {"/Administration"})
@jakarta.servlet.annotation.MultipartConfig
public class Administration extends HttpServlet {

    private AdministrationServices administrationServices = new AdministrationServices();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (administrationServices.getConnection() != null) {
            administrationServices.getInformation(req, resp);
        }

        if (action != null) {

            switch (action) {
                case "addCustomer":
                    administrationServices.addCustomer(req, resp);
                    break;
                case "addEmployee":
                    administrationServices.addEmployee(req, resp);
                    break;
                case "createAccount":
                    administrationServices.createAccount(req, resp);
                    break;
                case "updateUser":
                    administrationServices.updateUser(req, resp);
                    break;
                case "searchChanges":
                    administrationServices.searchChanges(req, resp);
                    break;
                default:
                    req.getRequestDispatcher("administration.jsp").forward(req, resp);
                    break;
            }

        } else {
            req.getRequestDispatcher("administration.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (administrationServices.getConnection() != null) {
            administrationServices.getInformation(req, resp);

        }

        if (action != null) {

            switch (action) {
                case "editUser":
                    administrationServices.editUser(req, resp);
                    break;
                default:
                    req.getRequestDispatcher("administration.jsp").forward(req, resp);
                    break;
            }

        } else {

            if (administrationServices.getConnection() == null) {
                Connection connection = (Connection) req.getSession().getAttribute("connection");
                administrationServices.setConnection(connection);
            }
            if (administrationServices.getAdmin() == null) {
                User user = (User) req.getSession().getAttribute("user");
                administrationServices.setAdmin(user);
            }
            administrationServices.getInformation(req, resp);

            req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);

        }
    }

}
