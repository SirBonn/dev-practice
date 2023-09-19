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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class UpdateUser {

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UpdateUser(Connection connection) {
        this.connection = connection;
    }

    public void updateUser(User user) {
        final String SQL_UPDATE = "UPDATE usuario SET nombre=?, direccion=?, sexo=?, password=?"
                + "WHERE codigo=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, user.getForename());
            preparedStatement.setString(2, user.getDirection());
            preparedStatement.setString(3, user.getGenre());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getCode());
            if(user.getUserType() ==1){
                updateEmployee(user);
            } else if (user.getUserType() == 2){
                updateCustomer(user);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println(e.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }
    }
    
    public void updateCustomer(User user){
        final String SQL_UPDATE = "UPDATE cliente SET birth=?"
                + "WHERE codigoUsuario=?";
        Customer customer = new Customer(user);
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, customer.getFormatedDate());
            preparedStatement.setInt(2, customer.getCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println(e.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }
        
    }
    
    public void updateEmployee(User user){
        final String SQL_UPDATE = "UPDATE empelado SET birth=?"
                + "WHERE codigoUsuario=?";
        Employee customer = new Employee(user);
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, customer.getTurno().getId());
            preparedStatement.setInt(2, customer.getCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println(e.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }
        
    }
}
