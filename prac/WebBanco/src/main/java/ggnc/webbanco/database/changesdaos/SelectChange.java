/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.changesdaos;

import ggnc.webbanco.database.models.userdaos.SelectUser;
import ggnc.webbanco.domain.Change;
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
public class SelectChange {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectChange(Connection connection) {
        this.connection = connection;
    }

    public List<Change> getChanges(int usrModified, User usrAdmin) throws InvalidActionException {
        List<Change> changes = new ArrayList<>();

        String SQL_SELECT_CHANGES_OF = "SELECT * FROM cambioRealizado "
                + "WHERE codUsuarioModificado = ? AND codGerente =?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_CHANGES_OF);
            preparedStatement.setInt(1, usrModified);
            preparedStatement.setInt(2, usrAdmin.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("fecha");
                String time = resultSet.getString("hora");
                User user = new SelectUser(connection).getUser(new User(usrModified));
                Change change = new Change(id, usrAdmin, user, time, date);

                changes.add(change);

            }

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new InvalidActionException("ha ocurrido un error en DB: " + ex.getMessage());
        }

        return changes;
    }

}
