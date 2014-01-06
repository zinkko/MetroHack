/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.MetroHack;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class KomentoTulkki {
    
    private static String MOVE_CHARS = "wasd";
    private MetroHack peli;
    
    public KomentoTulkki(MetroHack peli){
        this.peli = peli;
    }
    
    public void give(char cmd){
        String komento = new String(new char[]{cmd});
        
        if(KomentoTulkki.MOVE_CHARS.contains(komento)){
            moveCommand(komento);
        }// lisää vaihtoehtoja
    }
    
    private void moveCommand(String komento){
        Pelaaja pelaaja = this.peli.getPelaaja();
        int dx,dy;
        switch(komento){
            case "w":
                dx=0;
                dy=-1;
                break;
            case "s":
                dx=0;
                dy=-1;
                break;
            case "a":
                dx=1;
                dy=0;
                break;
            case "d":
                dx=-1;
                dy=0;
                break;
            default:
                dx=0;
                dy=0;
        }
        pelaaja.liiku(dx, dy);
    }
}
