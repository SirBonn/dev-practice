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
public class Account {

    private int code = KeyDropper.dropKey();;
    private Customer customer;
    private Date createdDate;
    private double amount;

    public Account(int code){
        this.code = code;
    }
    
    public Account(int code, Customer customer, String createdDate, double amount) {
        this.code = code;
        this.customer = customer;
        this.createdDate = setParsedDate(createdDate);
        this.amount = amount;
    }

    public Account(int code, Customer customer, double amount) {
        this.code = code;
        this.customer = customer;
        this.createdDate = setNowDate();
        this.amount = amount;
    }

    public Account(Customer customer, String createdDate, double amount) {
        this.customer = customer;
        this.createdDate = setParsedDate(createdDate);
        this.amount = amount;
    }
    
    public Account(Customer customer, double amount) {
        this.customer = customer;
        this.createdDate = setNowDate();
        this.amount = amount;
    }

    public void setStringDate(String date){
        this.createdDate = setParsedDate(date);
    }
    
    private Date setNowDate() {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.now();
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        return fechaDate;
    }

    private Date setParsedDate(String date) {
        Date fd = null;
        try {

            LocalDate fechaLD = LocalDate.parse(date);
            fd = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha" + date + " por " + e);
            e.printStackTrace(System.out);
        }

        return fd;
    }

    public String getFormatedCreatedDate() {
        if (createdDate != null) {

            LocalDate localDate = this.createdDate.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
    }

}
