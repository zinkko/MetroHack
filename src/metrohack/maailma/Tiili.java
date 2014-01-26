/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import static metrohack.maailma.Tiilityyppi.*;

/**
 *
 * @author ilari
 */
public class Tiili {
    private final int x,y;
    //private char kuva; 
    private Tiilityyppi tyyppi;
    //private final Tiilityyppi tyyppi; korvattu finaalimuuttuja tavallisella by Aino
    
    public Tiili(int x, int y, Tiilityyppi t){
        this.x = x;
        this.y = y;
        this.tyyppi = t;
        //this.kuva = t.getKuva();
    }
    
    public void piirra(char[][] map){
        try{
            map[map.length-y][x] = this.tyyppi.getKuva();
        }catch(ArrayIndexOutOfBoundsException ex){
            //
        }
    }
    

    public Tiilityyppi getTyyppi(){
        return this.tyyppi;
    }
    
    public void setTyyppi(Tiilityyppi t){
        if (this.tyyppi == OVI || this.tyyppi == LAITURI){
            return;
        }
        this.tyyppi=t;
        
    }

    public int[] getSijainti(){
        int[] sijainti = new int[2];
        sijainti[0]=x;
        sijainti[1]=y;
        return sijainti;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
 
}
