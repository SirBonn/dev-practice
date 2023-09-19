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
@lombok.Getter@lombok.Setter
public class Employee extends User{
    
    private Turno turno;

    public Employee(Turno turno, String forename, String direction, String numberID, String genre, String password) {
        super(forename, direction, numberID, genre, password, 1);
        this.turno = turno;
    }

    public Employee(User user){
        super(user.getCode(), user.getForename(), user.getDirection(), user.getNumberID(), user.getGenre(), user.getPassword(), 1);
    }
    
    
}
