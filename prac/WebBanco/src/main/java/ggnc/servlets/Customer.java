/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets;

import ggnc.servlets.services.CustomerServices;
import ggnc.webbanco.domain.User;
import java.io.IOException;
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
@WebServlet(name = "Customer", urlPatterns = {"/Customer"})
public class Customer extends HttpServlet {

    CustomerServices customerServices = new CustomerServices();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (customerServices.getConnection() != null) {
            customerServices.getInformation(req, resp);
        }

        if (action != null) {

            switch (action) {
                case "ownTransfer":
                    customerServices.transfer(req, resp);
                    break;
                case "thirdTransfer":
                    customerServices.transfer(req, resp);
                    break;
                case "requestAsociation":
                    customerServices.requestAsociation(req, resp);
                    break;

            }

        } else {
            //request.getSession().setAttribute("userLogged", user);
            req.getRequestDispatcher("customer.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action != null) {

            switch (action) {
                case "aceptRequest":
                    customerServices.aceptRequest(req, resp);
                    break;
                case "rejectRequest":
                    customerServices.rejectRequest(req, resp);
                    break;
            }

        } else {
            if (customerServices.getConnection() == null) {
                Connection connection = (Connection) req.getSession().getAttribute("connection");
                customerServices.setConnection(connection);
            }
            if (customerServices.getCustomer() == null) {
                User user = (User) req.getSession().getAttribute("user");
                customerServices.setCustomer(user);
            }
            customerServices.getInformation(req, resp);

            req.getRequestDispatcher("/pages-rscs/customers/custom-main.jsp").forward(req, resp);

        }
    }

}
