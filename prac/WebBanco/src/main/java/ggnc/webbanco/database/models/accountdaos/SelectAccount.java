/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.accountdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Account;
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
public class SelectAccount {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectAccount(Connection connection) {
        this.connection = connection;
    }

    public List<Account> getAllAccounts() {
        List<Account> allAccounts = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT * FROM cuenta";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("codigo");
                int usrCode = resultSet.getInt("codigoCliente");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(usrCode));
                String createdDate = resultSet.getString("fechaCreacion");
                double amount = (double) resultSet.getFloat("saldo");

                allAccounts.add(new Account(id, customer, createdDate, amount));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return allAccounts;
    }

    public List<Account> getUserAccounts(User user){
        List<Account> userAccounts = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT * FROM cuenta WHERE codigoCliente=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, user.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("codigo");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(user.getCode()));
                String createdDate = resultSet.getString("fechaCreacion");
                double amount = (double) resultSet.getFloat("saldo");

                userAccounts.add(new Account(id, customer, createdDate, amount));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return userAccounts;
    }
    
    public List<Account> getTopAccounts() {
        List<Account> topAccounts = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT c.codigoUsuario, u.nombre, SUM(cu.saldo) AS saldo_total FROM cliente c "
                + "INNER JOIN cuenta cu ON c.codigoUsuario = cu.codigoCliente "
                + "INNER JOIN usuario u ON c.codigoUsuario= u.codigo GROUP BY c.codigoUsuario, u.nombre "
                + "ORDER BY saldo_total DESC LIMIT 10";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int usrCode = resultSet.getInt("codigoUsuario");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(usrCode));
                double amount = (double) resultSet.getFloat("saldo_total");

                topAccounts.add(new Account(customer, amount));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return topAccounts;
    }

    public Account getAccount(Account account){
        
        String SQL_SELECT_BY_CODE = "SELECT * FROM cuenta WHERE codigo=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, account.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("codigoCliente");
                Customer customer = (Customer) new SelectUser(connection).getUser(new User(id));
                account.setCustomer(customer);
                String createdDate = resultSet.getString("fechaCreacion");
                account.setStringDate(createdDate);
                double amount = (double) resultSet.getFloat("saldo");
                account.setAmount(amount);
                

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }
        
        return  account;
    }
    
}
