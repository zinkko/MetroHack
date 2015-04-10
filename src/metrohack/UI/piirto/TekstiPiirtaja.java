/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI.piirto;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import metrohack.UI.Näkymä;
import metrohack.UI.Käyttöliittymä;
import metrohack.maailma.entities.Hahmo;
import metrohack.maailma.entities.Pelaaja;
import metrohack.maailma.entities.Reppu;


/**
 *
 * @author ilari
 */
public class TekstiPiirtaja implements Piirtaja {
    //private HashSet set;
    private int leveys,korkeus;
    private Käyttöliittymä ui;
    private JTextArea piirtoAlusta;
    private JPanel paneeli;
    private String[] print;
    private String pitkaKomento = "";
    private Näkymä näkymä;
    
    public TekstiPiirtaja(JTextArea teksti,int w, int h, Käyttöliittymä ui){
        this.piirtoAlusta = teksti;
        this.leveys = w;
        this.korkeus = h;
        this.ui = ui;
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
        kuva += statsbar();
        return kuva;
    }
    
    
    private String statsbar(){
        Pelaaja p = this.ui.getLogiikka().getPelaaja();
        int hp = p.getVitutus();
        //int mana = p.getMana();
        ArrayList<String> buffs = p.getBuffNames();
        String stats =  "\tHP "+hp+" | "+p.getNimi(); //+" | mana "+mana;
        stats = buffs.stream().map((buff) -> " | "+buff).reduce(stats, String::concat);
        return stats;
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

        ui.getLogiikka().getCurrentLevel().piirra(map);
    }
    
    private void paivitaHahmot(char[][] map){
        for (Hahmo h: ui.getLogiikka().getTaso().getHahmot()){
            try{
                map[h.getX()][h.getY()] = h.getMerkki();
            }catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("hahmo out of bounds" + h);
            }
        }
        Pelaaja p = ui.getLogiikka().getPelaaja();
        map[p.getX()][p.getY()] = '@';

    }

    public String getKomento(){
        return this.pitkaKomento.substring(1);
    }

    @Override
    public void setPitkaKomento(String s){
        this.pitkaKomento = s;
        this.piirra();
    }

    public void piirräReppu(Reppu reppu) {
        String kuva = "";
        for (int i = 0; i < korkeus; i++) {
            kuva += '|';
            for (int j = 1; j < leveys-1; j++) {
                if (i == 0 || i==korkeus-1){
                    kuva += '_';
                    continue;
                }
                kuva += '.';
            }
            kuva += "|\n";
        }
        this.piirtoAlusta.setText(kuva);
    }

    @Override
    public void vaihdaNäkymää(Näkymä seuraava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
