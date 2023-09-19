/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets.services;

import ggnc.webbanco.database.asociationsdaos.SelectAsociation;
import ggnc.webbanco.database.asociationsdaos.requestsdaos.SelectAsociationRequest;
import ggnc.webbanco.database.models.accountdaos.SelectAccount;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.database.tranferencesdaos.InserTransference;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.domain.Asociation;
import ggnc.webbanco.domain.AsociationRequest;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.Transference;
import ggnc.webbanco.domain.User;
import ggnc.webbanco.utils.InvalidActionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class EmployeeServices {

    private Connection connection;
    private User employee;

    public void getInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Customer> customers = new SelectUser(connection).getAllCustomers();
        req.setAttribute("customers", customers);
        List<Account> accounts = new SelectAccount(connection).getAllAccounts();
        req.setAttribute("accounts", accounts);

    }

    public void debTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectAccount sa = new SelectAccount(connection);
        int debAcc = Integer.parseInt(req.getParameter("debAccount"));
        Account debAccount = sa.getAccount(new Account(debAcc));
        double amount = Double.parseDouble(req.getParameter("amount"));

        Transference debTransfer = new Transference(debAccount, 1, amount, new SelectUser(connection).getUser(new User(101)));

        try {
            new InserTransference(connection).registerTransference(debTransfer);
            System.out.println("se realizo la transaccion");
            req.getSession().setAttribute("message", "se realizo la transaccion con exito");
            resp.sendRedirect("employee.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("employee.jsp");
        }

    }

    public void acTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectAccount sa = new SelectAccount(connection);
        int acAcc = Integer.parseInt(req.getParameter("acAccount"));
        Account acAccount = sa.getAccount(new Account(acAcc));
        double amount = Double.parseDouble(req.getParameter("amount"));

        Transference acTransfer = new Transference(acAccount, 0, amount, new SelectUser(connection).getUser(new User(101)));

        try {
            new InserTransference(connection).registerTransference(acTransfer);

            System.out.println("se realizo la transaccion");
            req.getSession().setAttribute("message", "se realizo la transaccion con exito");
            resp.sendRedirect("employee.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("employee.jsp");
        }

    }
}
