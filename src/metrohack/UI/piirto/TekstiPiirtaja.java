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
import metrohack.maailma.entities.Pelaaja;


/**
 *
 * @author ilari
 */
public class TekstiPiirtaja implements Piirtaja{
    private int leveys,korkeus;
    private Pelilogiikka peli;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    //private final int tulostusRivi = 25; //
    private String[] print;
    private String pitkaKomento = "";
    private boolean enableFriendDuck = false;
    
    public TekstiPiirtaja(JTextArea teksti,int w, int h, Pelilogiikka peli){
        this.piirtoAlusta = teksti;
        this.leveys = w;
        this.korkeus = h;
        this.peli = peli;
        this.print = new String[]{"hello","world","its","ilpo"};
    }
    
    public TekstiPiirtaja(JPanel paneeli){
        this.paneeli = paneeli;
    }
    
    @Override
    public void piirra(){
        if (this.piirtoAlusta!=null){
            char[][] map = new char[this.leveys][this.korkeus];
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
        String kuva = "$"+this.pitkaKomento + "\n";
        piirraAsiat(map);
        
        for (int y = this.korkeus-1; y>=0; y--){
            for (int x=0; x< this.leveys; x++){
                if (map[x][y]=='\000'){
                    kuva+=' ';
                    continue;
                }
                kuva += map[x][y];
            }
            kuva += '\n';
        }
        kuva += "\n";
        kuva += "\tHP 10 | kylläinen | mana 7 | buffs | ducks";
        return kuva;
    }
    
    @Override
    public void tulosta(String teksti){
        if (teksti.length()>this.leveys){
            teksti = teksti.substring(0, this.leveys);
        }      
        int tulostusRivi = korkeus - this.print.length;
        for (int i=1;i<this.print.length;i++){
            this.print[i-1] = this.print[i]; 
        }
        this.print[this.print.length-1] = teksti;
        this.piirra();
    }
    
    private void piirraAsiat(char[][] map){
        //piirrä alle
        this.paivitaHuoneet(map);
        //this.piirraAnkka(map);
        this.paivitaHahmot(map);
        this.piirraTulosteet(map);
        // piirrä päälle
    }
    
    private void piirraStatsit(){
        
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
            try{
                map[h.getX()][h.getY()] = h.getMerkki();
            }catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("hahmo out of bounds" + h);
            }
        }
        Pelaaja p = peli.getPelaaja();
        map[p.getX()][p.getY()] = '@';

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
    
    public void setPitkaKomento(String s){
        this.pitkaKomento = s;
        this.piirra();
    }
    /*
    public void addCharToCmd(char c){
        this.pitkaKomento += c;
        this.piirra();
    }
    
    public void backspace(){
        this.pitkaKomento = this.pitkaKomento.substring(0, this.pitkaKomento.length()-1);
        this.piirra();
    }*/
}
