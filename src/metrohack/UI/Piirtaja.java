/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import metrohack.MetroHack;
import metrohack.maailma.Taso;

/**
 *
 * @author ilari
 */
public class Piirtaja {
    private MetroHack peli;
    private char[][] map;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    
    public Piirtaja(JTextArea teksti){
        this.piirtoAlusta = teksti;
        
    }
    
    public Piirtaja(JPanel paneeli){
        this.paneeli = paneeli;
    }
    
    public void piirra(){
        if (this.piirtoAlusta==null){
            // graafinen
        }else{
            this.piirtoAlusta.setText(this.luoTekstiKuva());
        }
    }
    
    
    private String luoTekstiKuva(){
        String kuva = "";
        Taso taso = peli.getCurrentLevel();
        taso.piirra(map); // täytä kartta
        // piirrä kartta
        for (char[] taul:map){
            for (char c : taul){
                kuva = kuva + c + " ";
            }
            kuva = kuva + "\n";
        }
        return kuva;
    }
}
