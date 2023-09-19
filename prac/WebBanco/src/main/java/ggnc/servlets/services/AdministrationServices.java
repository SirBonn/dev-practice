/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets.services;

import ggnc.webbanco.database.changesdaos.InsertChange;
import ggnc.webbanco.database.changesdaos.SelectChange;
import ggnc.webbanco.database.models.accountdaos.InsertAccount;
import ggnc.webbanco.database.models.accountdaos.SelectAccount;
import ggnc.webbanco.database.models.turndaos.SelectTurno;
import ggnc.webbanco.database.models.userdaos.InsertUser;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.domain.Change;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.Employee;
import ggnc.webbanco.domain.Turno;
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
public class AdministrationServices {

    private Connection connection;
    private User admin;

    public void getInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Turno> turns = new SelectTurno(connection).getTurns();
        req.setAttribute("turns", turns);
        List<Employee> employees = new SelectUser(connection).getAllEmployees();
        req.setAttribute("employees", employees);
        List<Customer> customers = new SelectUser(connection).getAllCustomers();
        req.setAttribute("customers", customers);
        List<Account> accounts = new SelectAccount(connection).getAllAccounts();
        req.setAttribute("accounts", accounts);
        List<Account> topAccounts = new SelectAccount(connection).getTopAccounts();
        req.setAttribute("topAccounts", topAccounts);

    }

    public void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forename = req.getParameter("forename");
        String noID = req.getParameter("noID");
        String direction = req.getParameter("direction");
        String userPassword = req.getParameter("userpassword");
        String genre = req.getParameter("genre");
        String birthDate = req.getParameter("birthday");
        //getPDF

        Customer customer = new Customer(birthDate, forename, direction, noID, genre, userPassword);

        try {
            new InsertUser(connection).addUser(customer);

            System.out.println("se agrego al usuario " + customer.toString());
            req.getSession().setAttribute("message", "Se ha agregado el nuevo usuario: " + customer.getForename());
            resp.sendRedirect("administration.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            resp.sendRedirect("administration.jsp");
        }

    }

    public void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forename = req.getParameter("forename");
        String noID = req.getParameter("noID");
        String direction = req.getParameter("direction");
        String userPassword = req.getParameter("userpassword");
        String genre = req.getParameter("genre");
        int turnId = Integer.parseInt(req.getParameter("turn"));
        Turno turn = new SelectTurno(connection).getTurn(new Turno(turnId));
        Employee employee = new Employee(turn, forename, direction, noID, genre, userPassword);
        try {

            new InsertUser(connection).addUser(employee);

            System.out.println("se agrego al usuario " + employee.toString());
            req.getSession().setAttribute("message", "Se ha agregado el nuevo usuario: " + employee.getForename());
            resp.sendRedirect("administration.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            resp.sendRedirect("administration.jsp");
        }
    }

    public void createAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userCode = Integer.parseInt(req.getParameter("usrCode"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        Customer customer = (Customer) new SelectUser(connection).getUser(new User(userCode));

        Account account = new Account(customer, amount);

        try {

            new InsertAccount(connection).insertNewAccount(account);
            System.out.println("se agrego la cuenta");
            req.getSession().setAttribute("message", "Se ha agregado la cuenta a nombre de: \n\t"
                    + account.getCustomer().getCode() + " - " + account.getCustomer().getForename());
            resp.sendRedirect("administration.jsp");

        } catch (InvalidActionException ex) {
            req.getSession().setAttribute("errorMessage", "hubo un error al la cuenta, por el error " + ex.getMessage());
            resp.sendRedirect("administration.jsp");
        }

    }

    public void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int code = Integer.parseInt(req.getParameter("code"));
        User user = new User(code);
        int lvlAc = Integer.parseInt(req.getParameter("userType"));

        switch (lvlAc) {
            case 1:
                User employee = new SelectUser(connection).getUser(new Employee(user));
                req.setAttribute("editUser", employee);
                break;
            case 2:
                User customer = new SelectUser(connection).getUser(new Customer(user));
                req.setAttribute("editUser", customer);
                break;
        }

        req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);

    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int lvlAc = Integer.parseInt(req.getParameter("userType"));
            int code = Integer.parseInt(req.getParameter("code"));
            User user = new User(code);
            String forename = req.getParameter("forename");
            user.setForename(forename);
            String direction = req.getParameter("direction");
            user.setDirection(direction);
            String genre = req.getParameter("genre");
            user.setGenre(genre);

            switch (lvlAc) {
                case 1:
                    int turnId = Integer.parseInt(req.getParameter("turn"));
                    Turno turn = new SelectTurno(connection).getTurn(new Turno(turnId));
                    Employee employee = new Employee(user);
                    employee.setTurno(turn);

                    break;
                case 2:
                    Customer customer = new Customer(user);
                    String birthDate = req.getParameter("birthday");
                    customer.setBirthday(birthDate);

                    break;
            }

            new InsertChange(connection).newChange(new Change(admin, user));
            req.getSession().setAttribute("message", "Se ha registrado un cambio.");
            req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);
        } catch (InvalidActionException ex) {
            System.out.println(ex.getMessage());
            req.getSession().setAttribute("errorMessage", "hubo un error: " + ex.getMessage());
            req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);
        }

    }

    public void searchChanges(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int userCode = Integer.parseInt(req.getParameter("usrCode"));
            List<Change> changesReq = new SelectChange(connection).getChanges(userCode, admin);

            req.getSession().setAttribute("changesReq", changesReq);
        } catch (InvalidActionException | NumberFormatException ex) {
            req.getSession().setAttribute("errorMessage", "hubo un error: " + ex.getMessage());
            req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/pages-rscs/administration/admin-main.jsp").forward(req, resp);

    }
}
