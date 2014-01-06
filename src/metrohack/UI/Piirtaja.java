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
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    
    public Piirtaja(JTextArea teksti,int w, int h){
        this.piirtoAlusta = teksti;
        this.map = new char[w][h];
    }
    
    public Piirtaja(JPanel paneeli){
        this.paneeli = paneeli;
    }
    
    public void piirra(){
        if (this.piirtoAlusta==null){
            // graafinen
        }else{
            System.out.println("'sup");
            this.piirtoAlusta.setText(this.luoTekstiKuva());
        }
    }
    
    /**
     * @return kuva tekstimuodossa 
     */
    private String luoTekstiKuva(){
        String kuva = "";
        piirraAsiat();
        
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
    
    
    private void piirraAsiat(){
        //piirrä alle
        //this.paivitaHuoneet();
        //this.paivitaHahmot();
        this.piirraAnkka();
        // piirrä päälle
    }
    
    private void paivitaHuoneet(){
        peli.getCurrentLevel().piirra(map);
    }
    
    private void paivitaHahmot(){
        peli.getPelaaja().piirra(map);

    }
    
    private void piirraAnkka(){
        if (map==null){
            this.piirtoAlusta.setText("wtf???");
            return;
        }
        map[4][5] = '(';
        map[4][6] = '^';
        map[4][7] = ')';
        map[4][8] = '>';
        map[5][2] = '(';
        for (int i=3;i<7;i++){
            map[5][i] = '_';
        }
        map[5][7] = ')';
    }
}
