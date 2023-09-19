/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.domain;

import ggnc.webbanco.utils.KeyDropper;
import ggnc.webbanco.utils.PasswordEncoder;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class User {

    private int code = KeyDropper.dropKey();
    private String forename;
    private String direction;
    private String numberID;
    private String genre;
    private String password;
    private int userType;

    public User(int code, String password) {
        this.code = code;
        this.password = PasswordEncoder.encodePassword(password);
        this.userType = -1;

    }

    public User(int code) {
        this.code = code;
    }

    public User(String forename, String direction, String numberID, String genre, String password, int userType) {
        this.forename = forename;
        this.direction = direction;
        this.numberID = numberID;
        this.genre = genre;
        this.password = PasswordEncoder.encodePassword(password);
        this.userType = userType;
    }

    public User(int code, String forename, String direction, String numberID, String genre, String password, int userType) {
        this.code = code;
        this.forename = forename;
        this.direction = direction;
        this.numberID = numberID;
        this.genre = genre;
        this.password = password;
        this.userType = userType;
    }

    public User(int code, String forename, String ID, int typeUsr) {
        this.code = code;
        this.forename = forename;
        this.numberID = ID;
        this.userType = typeUsr;
    }

}
