/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

import ggnc.webbanco.utils.KeyDropper;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class AsociationRequest {

    private int id = KeyDropper.dropKey();
    private User customer;
    private Account account;
    private Date dateRequest;
    private int state;

    public AsociationRequest(int id){
        this.id = id;
    }
    
    public AsociationRequest(User customer, Account account) {
        this.customer = customer;
        this.account = account;
        this.dateRequest = setNowDate();
        this.state = 0;
    }

    public AsociationRequest(int id, User customer, Account account, String dateRequest, int state) {
        this.id = id;
        this.customer = customer;
        this.account = account;
        this.dateRequest = setDate(dateRequest);
        this.state = state;
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

    public void setStringDate(String date) {
        this.dateRequest = setDate(date);
    }

    private Date setDate(String fecha) {
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

        if (dateRequest != null) {

            LocalDate localDate = this.dateRequest.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
    }

}
