/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.accountdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.domain.Transference;
import ggnc.webbanco.utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class UpdateAccount {

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UpdateAccount(Connection connection) {
        this.connection = connection;
    }

    public void updateAccount(Transference transference) throws InvalidActionException {

        String SQL_UPDATE_ACCOUNT = "UPDATE cuenta SET saldo=? WHERE codigo=?";
        double newAmount = getNewAmount(transference);
        try {

            preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT);

            preparedStatement.setDouble(1, newAmount);
            preparedStatement.setInt(2, transference.getAccount().getCode());
            preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " + ex.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }

    }

    private double getNewAmount(Transference transference) {
        switch (transference.getType()) {
            case 0:
                return (transference.getAmount() + transference.getAccountAmount());
            case 1:
                return ( transference.getAccountAmount() - transference.getAmount() );
            default:
                return transference.getAccountAmount();
        }

    }

}
