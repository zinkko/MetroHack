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
public class Kuuntelija implements KeyListener{
    
    //private Modifier mod;
    private final KomentoTulkki tulkki;
    
    public Kuuntelija(KomentoTulkki t){
        this.tulkki = t;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        this.tulkki.give(e.getKeyChar());
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
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

