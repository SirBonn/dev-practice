/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.userdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.Employee;
import ggnc.webbanco.domain.User;
import ggnc.webbanco.utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertUser {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public InsertUser(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws InvalidActionException{

        String SQL_INSERT_USUARIO = "INSERT INTO usuario (codigo, nombre, direccion, noIdentificacion, sexo, password, tipoUsuario)"
                + " VALUES (?, ?,  ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USUARIO);

            preparedStatement.setInt(1, user.getCode());
            preparedStatement.setString(2, user.getForename());
            preparedStatement.setString(3, user.getDirection());
            preparedStatement.setString(4, user.getNumberID());
            preparedStatement.setString(5, user.getGenre());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getUserType());
            preparedStatement.execute();

            if (user.getUserType() == 2) {
                Customer customer = (Customer) user;
                addCustomer(customer);
            } else if (user.getUserType() == 1) {
                Employee employee = (Employee) user;
                addEmployee(employee);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " +ex.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }

    }

    private void addCustomer(Customer customer) throws SQLException {

//        String SQL_INSERT_USUARIO = "INSERT INTO cliente (codigoUsuario, birth, pdfDPI)"
//                + " VALUES (?, ?, ?)";
        String SQL_INSERT_USUARIO = "INSERT INTO cliente (codigoUsuario, birth)"
                + " VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(SQL_INSERT_USUARIO);
        
        preparedStatement.setInt(1, customer.getCode());
        preparedStatement.setString(2, customer.getFormatedDate());
        //setpdfDPI
        //preparedStatement.setInt(1, customer.getCode());

        preparedStatement.execute();

    }

    private void addEmployee(Employee empoloyee) throws SQLException {

        String SQL_INSERT_USUARIO = "INSERT INTO empleado (codigoUsuario, idTurno)"
                + " VALUES (?, ?)";

        preparedStatement = connection.prepareStatement(SQL_INSERT_USUARIO);

        preparedStatement.setInt(1, empoloyee.getCode());
        preparedStatement.setInt(2, empoloyee.getTurno().getId());

        preparedStatement.execute();
    }

}
