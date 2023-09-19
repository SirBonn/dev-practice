/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.accountdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertAccount {
    
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InsertAccount(Connection connection) {
        this.connection = connection;
    }
    
    public void insertNewAccount(Account account) throws InvalidActionException{
        
        String SQL_INSERT_ACCOUNT = "INSERT INTO cuenta (codigo, codigoCliente, fechaCreacion, saldo) "
                + "VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_ACCOUNT);

            preparedStatement.setInt(1, account.getCode());
            preparedStatement.setInt(2, account.getCustomer().getCode());
            preparedStatement.setString(3, account.getFormatedCreatedDate());
            preparedStatement.setDouble(4, account.getAmount());

            preparedStatement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " +ex.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }
        
    }
    
}
