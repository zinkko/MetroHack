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
    //private final int tulostusRivi = 25; //
    private String[] print;
    
    public Piirtaja(JTextArea teksti,int w, int h, MetroHack peli){
        this.piirtoAlusta = teksti;
        this.width = w;
        this.height = h;
        this.peli = peli;
        this.print = new String[]{"hello","world","its","ilpo"};
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
        if (teksti.length()>this.width){
            teksti = teksti.substring(0, this.width);
        }      
        int tulostusRivi = height - this.print.length;
        for (int i=1;i<this.print.length;i++){
            this.print[i-1] = this.print[i]; 
        }
        this.print[this.print.length-1] = teksti;
        this.piirra();
    }
    
    private void piirraAsiat(char[][] map){
        //piirrä alle
        this.paivitaHuoneet(map);
        this.paivitaHahmot(map);
        this.piirraAnkka(map);
        this.piirraTulosteet(map);
        // piirrä päälle
    }
    
    private void piirraTulosteet(char[][] map){
        int alkuRivi = map.length - this.print.length;
        for (int i=0;i<this.print.length;i++){
            for (int j=0;j<this.print[i].length();j++){
                map[alkuRivi+i][j] = print[i].charAt(j);
            }
        }
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
