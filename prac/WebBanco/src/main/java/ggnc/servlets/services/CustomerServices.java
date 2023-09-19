/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets.services;

import ggnc.webbanco.database.asociationsdaos.SelectAsociation;
import ggnc.webbanco.database.asociationsdaos.requestsdaos.InsertAsociationRequest;
import ggnc.webbanco.database.asociationsdaos.requestsdaos.SelectAsociationRequest;
import ggnc.webbanco.database.asociationsdaos.requestsdaos.UpdateAsociationRequest;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class CustomerServices {

    private Connection connection;
    private User customer;

    public void getInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Customer> customers = new SelectUser(connection).getAllCustomers();
        req.setAttribute("customers", customers);
        List<Account> accounts = new SelectAccount(connection).getUserAccounts(customer);
        req.setAttribute("userAccounts", accounts);
        List<AsociationRequest> pendingRequests = new SelectAsociationRequest(connection).getPendingRequests(customer);
        req.setAttribute("pendingRequests", pendingRequests);
        List<AsociationRequest> aceptedRequests = new SelectAsociationRequest(connection).getAceptedRequests(customer);
        req.setAttribute("aceptedRequests", aceptedRequests);
        List<Asociation> asociations = new SelectAsociation(connection).getAllAsociations(customer);
        req.setAttribute("asociations", asociations);

    }

    public void transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectAccount sa = new SelectAccount(connection);
        int acAcc = Integer.parseInt(req.getParameter("acAccount"));
        Account acAccount = sa.getAccount(new Account(acAcc));
        int debAcc = Integer.parseInt(req.getParameter("debAccount"));
        Account debAccount = sa.getAccount(new Account(debAcc));
        double amount = Double.parseDouble(req.getParameter("amount"));

        Transference acTransfer = new Transference(acAccount, 0, amount, new SelectUser(connection).getUser(new User(101)));
        Transference debTransfer = new Transference(debAccount, 1, amount, new SelectUser(connection).getUser(new User(101)));

        try {
            new InserTransference(connection).registerTransference(debTransfer, acTransfer);

            System.out.println("se realizo la transaccion");
            req.getSession().setAttribute("message", "se realizo la transaccion con exito");
            resp.sendRedirect("customer.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("customer.jsp");
        }

    }

    public void requestAsociation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectAccount sa = new SelectAccount(connection);
        int accountCode = Integer.parseInt(req.getParameter("accountCode"));
        Account accounToAsociate = sa.getAccount(new Account(accountCode));

        AsociationRequest asociationRequest = new AsociationRequest(customer, accounToAsociate);

        try {
            new InsertAsociationRequest(connection).newAsociationRequest(asociationRequest);

            System.out.println("se realizo ha enviado al solicitud");
            req.getSession().setAttribute("message", "se realizo la transaccion con exito");
            resp.sendRedirect("customer.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("customer.jsp");
        }

    }

    public void aceptRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int reqId = Integer.parseInt(req.getParameter("reqId"));
        AsociationRequest asociationRequest = new SelectAsociationRequest(connection).getRequest(new AsociationRequest(reqId));
        try {
            new UpdateAsociationRequest(connection).aceptRequest(asociationRequest);
            System.out.println("se acepto la solicitud");
            req.getSession().setAttribute("message", "se ha aceptado la solicitud");
            resp.sendRedirect("customer.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("customer.jsp");
        }

    }

    public void rejectRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int reqId = Integer.parseInt(req.getParameter("reqId"));
        AsociationRequest asociationRequest = new SelectAsociationRequest(connection).getRequest(new AsociationRequest(reqId));
        try {
            new UpdateAsociationRequest(connection).rejectRequest(asociationRequest);
            System.out.println("se acepto la solicitud");
            req.getSession().setAttribute("message", "se ha aceptado la solicitud");
            resp.sendRedirect("customer.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "se presento el error: " + ex.getMessage());
            resp.sendRedirect("customer.jsp");
        }

    }
}
