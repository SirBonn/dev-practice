/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.webbanco.utils;

import java.util.Random;

/**
 *
 * @author sirbon
 */
public class KeyDropper {

    public static int dropKey() {
        int key;

        int randomInt = new Random().nextInt(99999);

        key = (randomInt);

        return key;
    }

}
