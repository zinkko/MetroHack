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
public abstract class Hahmo {
    
    private Tiili paikka;
    private int x,y;
    private char merkki;
    
    public Hahmo(){
        this.merkki = '@';
    }
    
    public void piirra(char[][] map){
        map[x][y] = merkki;
    }
}
