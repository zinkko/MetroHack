/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI.piirto;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import metrohack.logiikka.Pelilogiikka;
import metrohack.maailma.entities.Hahmo;


/**
 *
 * @author ilari
 */
public class TekstiPiirtaja implements Piirtaja{
    private int width,height;
    private Pelilogiikka peli;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    //private final int tulostusRivi = 25; //
    private String[] print;
    private String pitkaKomento = null;
    private boolean enableFriendDuck = false;
    
    public TekstiPiirtaja(JTextArea teksti,int w, int h, Pelilogiikka peli){
        this.piirtoAlusta = teksti;
        this.width = w;
        this.height = h;
        this.peli = peli;
        this.print = new String[]{"hello","world","its","ilpo"};
    }
    
    public TekstiPiirtaja(JPanel paneeli){
        this.paneeli = paneeli;
    }
    
    @Override
    public void piirra(){
        if (this.piirtoAlusta!=null){
            char[][] map = new char[this.height][this.width];
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
        for (char[] t:map){
            for (char c:t){
                if (c=='\u0000'){
                    c = ' ';
                }
                kuva = kuva + c;
            }
            kuva = kuva + "\n";
        }           
        return kuva;
    }
    
    @Override
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
        this.piirraAnkka(map);
        this.paivitaHahmot(map);
        this.piirraTulosteet(map);
        this.piirraStatsit(map);
        //if (pitkaKomento != null) this.piirraTaikoja(map);
        // piirrä päälle
    }
    
    private void piirraStatsit(char[][] map){
        //List<Stat> stats = this.peli.getPelaaja().getStats();
        // odottaa implementaatiota...
    }
    
    private void piirraTaikoja(char[][] map){
        //map[0][0] = Komentotulkki.CMD_CHAR;
        //for (int i=0;i<this.pitkaKomento.length();i++){
        //    map[0][i+1] = this.pitkaKomento.charAt(i);
        //}
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
        for (Hahmo h: peli.getTaso().getHahmot()){
            map[h.getX()][h.getY()] = h.getMerkki();
        }

    }
    
    public void piirraYstavaAnkka(){
         this.enableFriendDuck = true;
    }
    
    private void piirraYstavaAnkka(char[][] map){
        map[4][10] = '<';
        map[4][11] = '(';
        map[4][12] = '^';
        map[4][13] = ')';
        map[5][11] = '(';
        for (int i=12;i<15;i++){
            map[5][i] = '_';
        }
        map[5][15] = ')';
    }
    public String getKomento(){
        return this.pitkaKomento.substring(1);
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
        
        if (this.enableFriendDuck){
            piirraYstavaAnkka(map);
        }
    }
    
    @Override
    public void naytaKomento(String komento){
        this.pitkaKomento = komento;
        piirra();
    }
    
    /*public void setPitkaKomento(String s){
        this.pitkaKomento = s;
        this.piirra();
    }
    
    public void addCharToCmd(char c){
        this.pitkaKomento += c;
        this.piirra();
    }
    
    public void backspace(){
        this.pitkaKomento = this.pitkaKomento.substring(0, this.pitkaKomento.length()-1);
        this.piirra();
    }*/
}
