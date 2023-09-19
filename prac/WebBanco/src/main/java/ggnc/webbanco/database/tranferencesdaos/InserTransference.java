/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.tranferencesdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.accountdaos.UpdateAccount;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Transference;
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
public class InserTransference {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private User cashier;

    public InserTransference(Connection connection) {
        this.connection = connection;
        this.cashier = new SelectUser(connection).getUser(new User(101));
    }

    public void registerTransference(Transference debTransference, Transference acrTransference) throws InvalidActionException {
        registerTransaction(debTransference);
        registerTransaction(acrTransference);
    }
     public void registerTransference(Transference transference) throws InvalidActionException {
        registerTransaction(transference);
    }

    private void registerTransaction(Transference transference) throws InvalidActionException {

        String SQL_INSERT_DEBIT_TRANSFER = "INSERT INTO transaccion (codigo, codCuenta, tipo, fecha, hora, monto, codCajero, saldoCuenta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_DEBIT_TRANSFER);

            preparedStatement.setInt(1, transference.getCode());
            preparedStatement.setInt(2, transference.getAccount().getCode());
            preparedStatement.setString(3, transference.getTypeString());
            preparedStatement.setString(4, transference.getFormatedDate());
            preparedStatement.setString(5, transference.getFormatedTime());
            preparedStatement.setDouble(6, transference.getAmount());
            preparedStatement.setInt(7, cashier.getCode());
            preparedStatement.setDouble(8, transference.getAccountAmount());

            preparedStatement.execute();

            new UpdateAccount(connection).updateAccount(transference);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " + ex.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }

    }

}
