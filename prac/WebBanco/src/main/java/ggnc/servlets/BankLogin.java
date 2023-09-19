/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.servlets;

import ggnc.servlets.services.LoginServices;
import ggnc.webbanco.domain.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sirbon
 */
@WebServlet(name = "BankLogin", urlPatterns = {"/BankLogin"})
public class BankLogin extends HttpServlet {

    private LoginServices loginServices;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.loginServices = new LoginServices();
        User user = loginServices.loginUser(req, resp);
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("connection", loginServices.getConnection());
        redirectUser(user, req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("action");

        if (accion != null) {

            switch (accion) {
                case "signOut":
                    loginServices.closeSession(req, resp);
                    break;
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    /*
    * metodos creados para gestion del servlet
    *
     */
    private void redirectUser(User user, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (user.getUserType()) {
            case 0:
                req.getRequestDispatcher("administration.jsp").forward(req, resp);
//                dispatcher = getServletContext().getRequestDispatcher("/Administration");
//                dispatcher.forward(req, resp);
                break;
            case 1:
                req.getRequestDispatcher("employee.jsp").forward(req, resp);
//                dispatcher = getServletContext().getRequestDispatcher("/Employee");
//                dispatcher.forward(req, resp);
                break;
            case 2:
                req.getRequestDispatcher("customer.jsp").forward(req, resp);
//                dispatcher = getServletContext().getRequestDispatcher("/Customer");
//                dispatcher.forward(req, resp);
                break;
            default:
                req.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
        }
    }

}
