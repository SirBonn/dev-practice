/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.asociationsdaos.requestsdaos;

import ggnc.webbanco.database.DBConectionManager;
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
public class InsertAsociationRequest {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InsertAsociationRequest(Connection connection) {
        this.connection = connection;
    }

    public void newAsociationRequest(AsociationRequest asociationRequest) throws InvalidActionException {

        String SQL_INSERT_CHANGE = "INSERT INTO solicitudAsociacion (id, codCliente, codCuenta, fecha, estado) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_CHANGE);

            preparedStatement.setInt(1, asociationRequest.getId());
            preparedStatement.setInt(2, asociationRequest.getCustomer().getCode());
            preparedStatement.setInt(3, asociationRequest.getAccount().getCode());

            preparedStatement.setString(4, asociationRequest.getFormatedDate());
            preparedStatement.setInt(5, asociationRequest.getState());

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
