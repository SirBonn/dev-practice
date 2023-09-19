/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets;

import ggnc.servlets.services.EmployeeServices;
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
@WebServlet(name = "Employee", urlPatterns = {"/Employee"})
public class Employee extends HttpServlet {

    private final EmployeeServices employeeServices = new EmployeeServices();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (employeeServices.getConnection() != null) {
            employeeServices.getInformation(req, resp);
        }

        if (action != null) {

            switch (action) {
                case "debTransfer":
                    employeeServices.debTransfer(req, resp);
                    break;
                case "acTransfer":
                    employeeServices.acTransfer(req, resp);
                    break;

            }

        } else {
            req.getRequestDispatcher("employee.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action != null) {

            switch (action) {

            }

        } else {
            if (employeeServices.getConnection() == null) {
                Connection connection = (Connection) req.getSession().getAttribute("connection");
                employeeServices.setConnection(connection);
            }
            if (employeeServices.getEmployee() == null) {
                User user = (User) req.getSession().getAttribute("user");
                employeeServices.setEmployee(user);
            }
            employeeServices.getInformation(req, resp);

            req.getRequestDispatcher("/pages-rscs/employees/emp-main.jsp").forward(req, resp);

        }
    }

}
