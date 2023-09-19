/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.asociationsdaos.requestsdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.accountdaos.SelectAccount;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.domain.AsociationRequest;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.User;
import ggnc.webbanco.utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sirbon
 */
public class SelectAsociationRequest {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectAsociationRequest(Connection connection) {
        this.connection = connection;
    }

    private List<AsociationRequest> getAllRequests(User user) {
        List<AsociationRequest> allRequests = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT * FROM solicitudAsociacion WHERE codCliente=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, user.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int usrCode = resultSet.getInt("codCliente");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(usrCode));
                int accCode = resultSet.getInt("codCuenta");
                Account account = new SelectAccount(connection).getAccount(new Account(accCode));
                String date = resultSet.getString("fecha");
                int state = resultSet.getInt("estado");

                allRequests.add(new AsociationRequest(id, customer, account, date, state));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return allRequests;
    }

    public List<AsociationRequest> getPendingRequests(User user) {
        List<AsociationRequest> pendingRequests = new ArrayList<>();
        List<AsociationRequest> allRequests = getAllRequests(user);

        for (AsociationRequest request : allRequests) {
            if (request.getState() == 0) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    public List<AsociationRequest> getAceptedRequests(User user) {
        List<AsociationRequest> aceptedRequests = new ArrayList<>();
        List<AsociationRequest> allRequests = getAllRequests(user);

        for (AsociationRequest request : allRequests) {
            if (request.getState() == 1) {
                aceptedRequests.add(request);
            }
        }
        return aceptedRequests;
    }

    public List<AsociationRequest> getRejectedRequests(User user) {
        List<AsociationRequest> rejectedRequests = new ArrayList<>();
        List<AsociationRequest> allRequests = getAllRequests(user);

        for (AsociationRequest request : allRequests) {
            if (request.getState() == 2) {
                rejectedRequests.add(request);
            }
        }
        return rejectedRequests;
    }
    
     public AsociationRequest getRequest(AsociationRequest request) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM solicitudAsociacion WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, request.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int usrCode = resultSet.getInt("codCliente");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(usrCode));
                request.setCustomer(customer);
                int accCode = resultSet.getInt("codCuenta");
                Account account = new SelectAccount(connection).getAccount(new Account(accCode));
                request.setAccount(account);
                String date = resultSet.getString("fecha");
                request.setStringDate(date);
                int state = resultSet.getInt("estado");
                request.setState(state);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return request;
    }


}
