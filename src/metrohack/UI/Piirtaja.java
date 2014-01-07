/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import metrohack.MetroHack;

/**
 *
 * @author ilari
 */
public class Piirtaja {
    private int width,height;
    private MetroHack peli;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    private int tulostusRivi = 25; // 
    
    public Piirtaja(JTextArea teksti,int w, int h, MetroHack peli){
        this.piirtoAlusta = teksti;
        this.width = w;
        this.height = h;
        this.peli = peli;
    }
    
    public Piirtaja(JPanel paneeli){
        this.paneeli = paneeli;
    }
    
    public void piirra(){
        if (this.piirtoAlusta==null){
            // graafinen
        }else{
            char[][] map = new char[this.width][this.height];
            this.piirtoAlusta.setText(this.luoTekstiKuva(map));
        }
    }
    
    public void piirra(char[][] map){
        this.piirtoAlusta.setText(this.luoTekstiKuva(map));
    }
    
    /**
     * @return kuva tekstimuodossa 
     */
    private String luoTekstiKuva(char[][] map){
        String kuva = "";
        piirraAsiat(map);
        for (char[] taul:map){
            for (char c : taul){
                
                if (c=='\u0000'){
                    c = ' ';
                }
                kuva = kuva + c;
            }
            kuva = kuva + "\n";
        }
        return kuva;
    }
    
    public void tulosta(String teksti){
        char[][] map = new char[width][height];
        int rivi = this.tulostusRivi;
        int j = 0;
        for (int i=0;i<teksti.length();i++){
            j=i%this.height;
            if (i!= 0 && j==0){
                rivi++;
            }
            map[rivi][j] = teksti.charAt(i);
        }
        this.piirra(map);
        
    }
    
    private void piirraAsiat(char[][] map){
        //piirrä alle
        this.paivitaHuoneet(map);
        this.paivitaHahmot(map);
        this.piirraAnkka(map);
        // piirrä päälle
    }
    
    private void paivitaHuoneet(char[][] map){

        peli.getCurrentLevel().piirra(map);
    }
    
    private void paivitaHahmot(char[][] map){
        peli.getPelaaja().piirra(map);

    }
    
    private void piirraAnkka(char[][] map){
        if (map==null){
            this.piirtoAlusta.setText("wtf???");
            return;
        }
        map[4][5] = '(';
        map[4][6] = '^';
        map[4][7] = ')';
        map[4][8] = '>';
        map[5][3] = '(';
        for (int i=4;i<7;i++){
            map[5][i] = '_';
        }
        map[5][7] = ')';
    }
}
