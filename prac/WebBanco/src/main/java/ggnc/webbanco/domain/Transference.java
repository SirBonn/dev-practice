/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

import ggnc.webbanco.utils.KeyDropper;
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
public class Transference {

    private int code = KeyDropper.dropKey();
    private Account account;
    private int type;
    private String typeString;
    private Date transferDate;
    private Time transferTime;
    private double amount;
    private User cashier;
    private double accountAmount;

    public Transference(int code, Account account, int type, String transferDate, String transferTime, double amount, User cashier) {
        this.code = code;
        this.account = account;
        this.type = type;
        selectTypeString(type);
        this.transferDate = changeDate(transferTime);
        this.transferTime = timeConverter(transferDate);
        this.amount = amount;
        this.cashier = cashier;
    }

    public Transference(Account account, int type, double amount, User cashier) {
        this.account = account;
        this.type = type;
        selectTypeString(type);
        this.amount = amount;
        this.cashier = cashier;
        this.accountAmount = account.getAmount();
        this.transferTime = setNowTime();
        this.transferDate = setNowDate();
    }

    private void selectTypeString(int type) {
        if (type == 0) {
            this.typeString = "credito";
        } else if (type == 1) {
            this.typeString = "debito";
        }
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

        if (transferDate != null) {

            LocalDate localDate = this.transferDate.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
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

    public String getFormatedTime() {

        if (transferTime != null) {

            LocalTime localTime = this.transferTime.toLocalTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formatedTime = localTime.format(formatter);

            return formatedTime;
        } else {
            return "";
        }
    }

}
