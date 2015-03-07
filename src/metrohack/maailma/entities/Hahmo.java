/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import metrohack.maailma.Taso;
import metrohack.maailma.Tiili;

/**
 *
 * @author ilari
 */
public abstract class Hahmo {

    protected Tiili paikka;
    protected char merkki;
    protected String nimi;
    protected int vitutus; //hit points at the moment
    protected int vitutusMax; //tässä kohtaa KUOLEE
    protected int x;
    protected int y;
    protected Taso tasoNyt;

    public Hahmo(int vitutusMax, String nimi, int x, int y) {
        this.vitutus = 0; //aina aluksi nollaa
        this.vitutusMax = vitutusMax;
        this.nimi = nimi;
        this.x = x;
        this.y = y;
    }

   /* public void piirra(char[][] map) {
        try{
            map[map.length - y][x] = merkki;
        }catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }*/
    
    public void asetaTaso(Taso t){
        tasoNyt = t;
    }
    
    @Override
    public String toString(){
        return this.nimi + "@"+x+","+y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public char getMerkki(){
        return merkki;
    }
    
    public boolean onkoPaikkaLaillinen(int x, int y){
        return false;
    }
}
