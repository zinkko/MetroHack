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
    POHJOINEN(0,1,"w"), ETELÄ(0,-1,"s"), ITÄ(1,0,"d"), LÄNSI(-1,0,"a"), KOILLINEN(1,1,"e"),
    KAAKKO(1,-1,"c"), LOUNAS(-1,-1, "z"), LUODE(-1,1,"q"), PAIKALLAAN(0,0,"x");
    
    private final int dx, dy;
    private final String merkkijono;
    
    private Suunta(int dx, int dy, String s){
        this.dx = dx;
        this.dy = dy;
        this.merkkijono = s;
    }
    
    public String merkkijono(){
        return this.merkkijono;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    
    
}
