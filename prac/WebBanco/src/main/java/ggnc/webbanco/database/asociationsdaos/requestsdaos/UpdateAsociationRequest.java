/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.asociationsdaos.requestsdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.asociationsdaos.InsertAsociation;
import ggnc.webbanco.database.models.accountdaos.SelectAccount;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.domain.Asociation;
import ggnc.webbanco.domain.AsociationRequest;
import ggnc.webbanco.domain.Customer;
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
public class UpdateAsociationRequest {

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UpdateAsociationRequest(Connection connection) {
        this.connection = connection;
    }

    private void updateAsociationRequest(AsociationRequest asociationRequest, int newState) throws InvalidActionException {

        String SQL_UPDTE_ASOCIATON = "UPDATE solicitudAsociacion SET estado=? WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDTE_ASOCIATON);
            preparedStatement.setInt(1, newState);
            preparedStatement.setInt(2, asociationRequest.getId());
            preparedStatement.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            throw new InvalidActionException("ocurrio un error actualizando:" +ex.getLocalizedMessage());

        } finally {

            DBConectionManager.close(preparedStatement);

        }

    }
    
    public void aceptRequest(AsociationRequest asociationRequest) throws InvalidActionException{
        updateAsociationRequest(asociationRequest, 1);
        Asociation asociation = new Asociation(asociationRequest.getCustomer(), asociationRequest.getAccount());
        new InsertAsociation(connection).newAsociationt(asociation);
    }
    
    public void rejectRequest(AsociationRequest asociationRequest) throws InvalidActionException{
        updateAsociationRequest(asociationRequest, 2);
    }
    
    
}
