/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets.services;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.Employee;
import ggnc.webbanco.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author sirbon
 */
@lombok.Setter
@lombok.Getter
public class LoginServices {

    private Connection connection;

    public LoginServices() {
        this.connection = DBConectionManager.getConnection();
    }

    public User loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int userCode = Integer.parseInt(req.getParameter("userCode"));
            String userpassword = req.getParameter("userPassword");

            User user = new SelectUser(connection).SearchByUser(new User(userCode, userpassword));

            return specificParams(user);
        } catch (NumberFormatException e) {

            return new User(-1);
        }

    }

    private User specificParams(User user) {
        switch (user.getUserType()) {
            case 1:
                Employee employee = new Employee(user);
                new SelectUser(connection).getEmployee(employee);
                return employee;
            case 2:
                Customer customer = new Customer(user);
                new SelectUser(connection).getCustomer(customer);
                return customer;
            default:
                return user;
        }
    }

    public void closeSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = (Connection) request.getSession().getAttribute("connection");
        //DBConectionManager.close(con);
        request.getSession().invalidate();
        
        response.sendRedirect("login.jsp");
    }

}
