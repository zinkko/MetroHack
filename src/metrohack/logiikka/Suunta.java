/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.logiikka;

/**
 *
 * @author ilari
 */
public enum Suunta {
    POHJOINEN(0,1), ETELÄ(0,-1), ITÄ(0,1), LÄNSI(-1,0), KOILLINEN(1,1),
    KAAKKO(1,-1), LOUNAS(-1,-1), LUODE(-1,1);
    
    private final int dx, dy;
    
    private Suunta(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    
    
}
