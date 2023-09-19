/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.asociationsdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.accountdaos.SelectAccount;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Account;
import ggnc.webbanco.domain.Asociation;
import ggnc.webbanco.domain.AsociationRequest;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.User;
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
public class SelectAsociation {
    
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectAsociation(Connection connection) {
        this.connection = connection;
    }

    public List<Asociation> getAllAsociations(User user) {
        List<Asociation> allAsociations = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT * FROM solicitudAsociacion WHERE codCliente=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, user.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int usrCode = resultSet.getInt("codCliente");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(usrCode));
                int accCode = resultSet.getInt("codCuenta");
                Account account = new SelectAccount(connection).getAccount(new Account(accCode));

                allAsociations.add(new Asociation(customer, account));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return allAsociations;
    }
}
