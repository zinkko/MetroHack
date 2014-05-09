/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import metrohack.maailma.Tiili;

/**
 *
 * @author ilari
 */
public abstract class Hahmo {

    protected Tiili paikka;
    protected char merkki;
    protected String nimi;
    protected int vitutus; //hit points
    protected int x;
    protected int y;

    public Hahmo(int vitutus, String nimi, int x, int y) {
        this.vitutus = vitutus;
        this.nimi = nimi;
        this.x = x;
        this.y = y;
    }

    public void piirra(char[][] map) {
        try{
            map[map.length - y][x] = merkki;
        }catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }
    
    public boolean onkoPaikkaLaillinen(int x, int y){
        return false;
    }
}
