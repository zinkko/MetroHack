/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import metrohack.UI.piirto.Piirtaja;

/**
 *
 * @author ilari
 */
public class Kuuntelija implements KeyListener{
    
    private final Komentotulkki tulkki;
    private final Piirtaja piirtaja;
    
    public Kuuntelija(Komentotulkki t, Piirtaja p){
        this.tulkki = t;
        this.piirtaja = p;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        char merkki = e.getKeyChar();
        if ((int) merkki == 65535){
            return;
        }
        this.tulkki.otaVastaanKomento(merkki);
        this.piirtaja.piirra();
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

