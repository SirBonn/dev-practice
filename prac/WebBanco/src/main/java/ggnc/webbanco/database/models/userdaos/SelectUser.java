/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.userdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.database.models.turndaos.SelectTurno;
import ggnc.webbanco.domain.Customer;
import ggnc.webbanco.domain.Employee;
import ggnc.webbanco.domain.Turno;
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
public class SelectUser {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectUser(Connection connection) {
        this.connection = connection;
    }

    public User SearchByUser(User user) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM usuario WHERE codigo = ? AND password = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, user.getCode());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("codigo");
                user.setCode(code);
                String forename = resultSet.getString("nombre");
                user.setForename(forename);
                String direction = resultSet.getString("direccion");
                user.setDirection(direction);
                String ID = resultSet.getString("noIdentificacion");
                user.setNumberID(ID);
                String genre = resultSet.getString("sexo");
                user.setGenre(genre);
                int typeUsr = resultSet.getInt("tipoUsuario");
                user.setUserType(typeUsr);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return user;
    }

    public User getUser(User user) {

        String SQL_SELECT = "SELECT * FROM usuario WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1, user.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("codigo");
                user.setCode(code);
                String forename = resultSet.getString("nombre");
                user.setForename(forename);
                String direction = resultSet.getString("direccion");
                user.setDirection(direction);
                String ID = resultSet.getString("noIdentificacion");
                user.setNumberID(ID);
                String genre = resultSet.getString("sexo");
                user.setGenre(genre);
                String password = resultSet.getString("password");
                user.setPassword(password);
                int typeUsr = resultSet.getInt("tipoUsuario");
                user.setUserType(typeUsr);

                if (user.getUserType() == 1) {
                    user = getEmployee(new Employee(user));
                } else if (user.getUserType() == 2) {
                    user = getCustomer(new Customer(user));
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return user;
    }

    public Employee getEmployee(Employee e) {
        String SQL_SELECT_EMP = "SELECT * FROM empleado WHERE codigoUsuario = ? ";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_EMP);
            preparedStatement.setInt(1, e.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                //int code = resultSet.getInt("code");
                int idTurno = resultSet.getInt("idTurno");

                e.setTurno(new SelectTurno(connection).getTurn(new Turno(idTurno)));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return e;
    }

    public Customer getCustomer(Customer c) {
        String SQL_SELECT_CUS = "SELECT * FROM cliente WHERE codigoUsuario = ? ";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_CUS);
            preparedStatement.setInt(1, c.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                //int code = resultSet.getInt("code");
                String birth = resultSet.getString("birth");
                c.setBirthday(birth);

                //c.setPDFDPI
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return c;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        String SQL_SELECT_ALL = "SELECT * FROM usuario";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("codigo");
                String forename = resultSet.getString("nombre");
                String ID = resultSet.getString("noIdentificacion");
                int typeUsr = resultSet.getInt("tipoUsuario");

                allUsers.add(new User(code, forename, ID, typeUsr));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return allUsers;
    }

    public List<Customer> getAllCustomers() {
        List<User> allUsers = getAllUsers();
        List<Customer> allCustomers = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUserType() == 2) {
                Customer customer = new Customer(user);
                allCustomers.add(getCustomer(customer));
            }
        }

        return allCustomers;
    }

    public List<Employee> getAllEmployees() {
        List<User> allUsers = getAllUsers();
        List<Employee> allEmployees = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUserType() == 1) {
                Employee employee = new Employee(user);
                allEmployees.add(getEmployee(employee));
            }
        }

        return allEmployees;
    }

}
