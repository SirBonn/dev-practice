/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.database.models.turndaos;

import ggnc.webbanco.database.DBConectionManager;
import ggnc.webbanco.domain.Turno;
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
public class SelectTurno {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SelectTurno(Connection connection) {
        this.connection = connection;
    }

    public List<Turno> getTurns() {
        List<Turno> turns = new ArrayList<>();

        String SQL_SELECT_BY_CODE = "SELECT * FROM turno";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String alias = resultSet.getString("nombre");
                String entryTime = resultSet.getString("horaEntrada");
                String leaveTime = resultSet.getString("horaSalida");

                turns.add(new Turno(id, alias, entryTime, leaveTime));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return turns;
    }

    public Turno getTurn(Turno turn) {
        
        String SQL_SELECT_BY_CODE = "SELECT * FROM turno WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, turn.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String alias = resultSet.getString("nombre");
                String entryTime = resultSet.getString("horaEntrada");
                String leaveTime = resultSet.getString("horaSalida");

                turn = new Turno(turn.getId(), alias, entryTime, leaveTime);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return turn;
    }

}
