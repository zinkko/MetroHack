/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

/**
 *
 * @author ilari
 */
   
public enum Tiilityyppi{
    LATTIA('.'),SEINA('#'),OVI('+'),LAITURI('=');

    private final char kuva;

    private Tiilityyppi(char kuva){
        this.kuva = kuva;
    }

    public char getKuva(){
        return kuva;
    }
}
