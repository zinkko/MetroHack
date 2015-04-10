/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ilari
 */
public class Kuuntelija implements KeyListener {

    private final Komentotulkki tulkki;

    public Kuuntelija(Komentotulkki t, Käyttöliittymä ui) {
        this.tulkki = t;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char merkki = e.getKeyChar();
        if ((int) merkki == 65535) {
            return;
        }
        
        this.tulkki.otaVastaanKomento(merkki);
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*
     private enum Modifier{
     SHIFT(KeyEvent.VK_SHIFT),CTRL(KeyEvent.VK_CONTROL),ALT(KeyEvent.VK_ALT);

     private final int keyCode;
        
     public int getCode(){
     return this.keyCode;
     }
        
     private Modifier(int code){
     this.keyCode = code;
     }
     }*/
}
