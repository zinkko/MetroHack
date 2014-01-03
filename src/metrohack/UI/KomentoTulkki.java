/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.MetroHack;

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
    
    public void give(char komento){
        if(KomentoTulkki.MOVE_CHARS.indexOf(komento)>=0){
            moveCommand(komento);
        }// lisää vaihtoehtoja
    }
    
    private void moveCommand(char komento){
        
    }
}
