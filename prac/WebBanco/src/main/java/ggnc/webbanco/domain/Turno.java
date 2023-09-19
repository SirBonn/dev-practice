/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

/**
 *
 * @author sirbon
 */
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@lombok.Getter
@lombok.Setter
public class Turno {

    private int id;
    private String alias;
    private Time entryTime;
    private Time leaveTime;

    public Turno(int id, String nombre, String entryTime, String leaveTime) {
        this.id = id;
        this.alias = nombre;
        this.entryTime = timeConverter(entryTime);
        this.leaveTime = timeConverter(leaveTime);
    }

    public Turno(int id) {
        this.id = id;
    }
    
    private Time timeConverter(String timeStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(timeStr);
        } catch (ParseException ex) {
            System.out.println("ocurrio un error convirtiendo la hora: \n" + ex.getMessage());
        }
        return new Time(date.getTime());
    }

    
 
}
