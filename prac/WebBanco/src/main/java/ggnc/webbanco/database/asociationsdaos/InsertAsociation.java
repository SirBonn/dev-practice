/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.asociationsdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.domain.Asociation;
import ggnc.webbanco.domain.AsociationRequest;
import ggnc.webbanco.utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertAsociation {
    
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InsertAsociation(Connection connection) {
        this.connection = connection;
    }

    public void newAsociationt(Asociation asociation) throws InvalidActionException {

        String SQL_INSERT_CHANGE = "INSERT INTO cuentaAsociada (codCliente, codCuenta) "
                + "VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_CHANGE);

            preparedStatement.setInt(1, asociation.getCustomer().getCode());
            preparedStatement.setInt(2, asociation.getThirdPartyAccount().getCode());


            preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " + ex.getMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }

    }
    
}
