/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sirbon
 */
@lombok.Getter@lombok.Setter
public class Customer extends User{
    
    private Date birthDate;
    private Object DPI;

    public Customer(String birthDate, Object DPI, String forename, String direction, String numberID, String genre, String password) {
        super(forename, direction, numberID, genre, password, 2);
        this.birthDate = setDateBirthday(birthDate);
        this.DPI = DPI;
    }

    public Customer(String birthDate, String forename, String direction, String numberID, String genre, String password) {
        super( forename, direction, numberID, genre, password, 2);
       this.birthDate = setDateBirthday(birthDate);
    }
    
    public Customer(User user){
        super(user.getCode(), user.getForename(), user.getDirection(), user.getNumberID(), user.getGenre(), user.getPassword(), 2);
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

    public void setBirthday(String fecha){
        this.birthDate = setDateBirthday(fecha);
    }
    
    private Date setDateBirthday(String fecha) {
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

        if (birthDate != null) {

            LocalDate localDate = this.birthDate.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
    }
    
}
