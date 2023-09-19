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
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Asociation {
    
    private User customer;
    private Account thirdPartyAccount;

    public Asociation(User customer, Account thirdPartyAccount) {
        this.customer = customer;
        this.thirdPartyAccount = thirdPartyAccount;
    }
    
    
    
}
