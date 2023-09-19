/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Change {

    private int id;
    private User admin;
    private User user;
    private Time changeTime;
    private Date changeDate;

    public Change(User admin, User user) {
        this.admin = admin;
        this.user = user;
        this.changeTime = setNowTime();
        this.changeDate = setNowDate();
    }

    public Change(int id, User admin, User user, String changeTime, String changeDate) {
        this.id = id;
        this.admin = admin;
        this.user = user;
        this.changeTime = timeConverter(changeTime);
        this.changeDate = changeDate(changeDate);
    }

    private Time timeConverter(String timeStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        java.util.Date date = null;
        
        try {
            date = sdf.parse(timeStr);
        } catch (ParseException ex) {
            System.out.println("ocurrio un error convirtiendo la hora: \n" + ex.getMessage());
        }
        return new Time(date.getTime());
    }

    private Date setNowDate() {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.now();
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha por " + e);
            e.printStackTrace(System.out);
        }
        return fechaDate;
    }

    private Time setNowTime() {
        String horaFormateada = null;
        try {

            LocalTime actualHour = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            horaFormateada = actualHour.format(formatter);
            
        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha por " + e);
            e.printStackTrace(System.out);
        }
        return timeConverter(horaFormateada);
    }

    private Date changeDate(String fecha) {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.parse(fecha);
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha" + fecha + " por " + e);
            e.printStackTrace(System.out);
        }

        return fechaDate;
    }

    public String getFormatedDate() {

        if (changeDate != null) {

            LocalDate localDate = this.changeDate.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
    }
    
    public String getFormatedTime() {

        if (changeDate != null) {

            LocalTime localTime = this.changeTime.toLocalTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formatedTime = localTime.format(formatter);

            return formatedTime;
        } else {
            return "";
        }
    }

}
