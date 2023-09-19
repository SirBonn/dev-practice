/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.changesdaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.utils.InvalidActionException;
import ggnc.webbanco.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertChange {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InsertChange(Connection connection) {
        this.connection = connection;
    }

    public void newChange(Change change) throws InvalidActionException {

        String SQL_INSERT_CHANGE = "INSERT INTO cambioRealizado (id, codGerente, codUsuarioModificado, fecha, hora) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_CHANGE);

            preparedStatement.setInt(1, change.getId());
            preparedStatement.setInt(2, change.getAdmin().getCode());
            preparedStatement.setInt(3, change.getUser().getCode());

            preparedStatement.setString(4, change.getFormatedDate());
            preparedStatement.setString(5, change.getFormatedTime());

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
