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
public class Tiili {
    private final int x,y;
    private char kuva; 
    
    public Tiili(int x, int y, char kuva){
        this.x = x;
        this.y = y;
        this.kuva = kuva;
    }
    
    public void piirra(char[][] map){
        map[this.x][this.y] = this.kuva;
    }
    
    public void setKuva(char c){
        this.kuva = c;
    }
}
