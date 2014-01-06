/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import metrohack.MetroHack;
import metrohack.maailma.entities.Hahmo;

/**
 *
 * @author ilari
 */
public class Piirtaja {
    private MetroHack peli;
    private char[][] map;
    private int width, height;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    
    public Piirtaja(JTextArea teksti,int w, int h){
        this.piirtoAlusta = teksti;
        this.map = new char[width][height];
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
    
    /**
     * @return kuva tekstimuodossa 
     */
    private String luoTekstiKuva(){
        String kuva = "";
        //piirrä alle
        this.paivitaHuoneet();
        this.paivitaHahmot();
        // piirrä päälle
        for (char[] taul:map){
            for (char c : taul){
                if (c=='\u0000'){
                    c = ' ';
                }
                kuva = kuva + c + " ";
            }
            kuva = kuva + "\n";
        }
        return kuva;
    }
    
    private void paivitaHuoneet(){
        peli.getCurrentLevel().piirra(map);
    }
    
    private void paivitaHahmot(){
        peli.getPelaaja().piirra(map);
        for (Hahmo h: peli.getCurrentLevel().getHahmot()){
            h.piirra(map);
        }
    }
}
