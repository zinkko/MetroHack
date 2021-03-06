/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.UI.piirto.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import metrohack.logiikka.Pelilogiikka;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Käyttöliittymä implements Runnable{
    
    private final JFrame frame;
    private final boolean isTextBased;
    private final Pelilogiikka peli;
    private Piirtaja piirtaja;
    private String fontName;
    
    public Käyttöliittymä(Pelilogiikka peli, boolean isTextBased){
        this.frame = new JFrame("MetroHack");
        this.isTextBased = isTextBased;
        this.peli = peli;     
    }
    
    public Käyttöliittymä(Pelilogiikka peli, String fontName){
        this(peli,true);
        this.fontName = fontName;
    }
    
    @Override
    public void run(){
        frame.setPreferredSize(new Dimension(900,700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //if (this.isTextBased){
            luoTekstiKomponentit(frame.getContentPane());
        //}else{
        //    luoGraafisetKomponentit(frame.getContentPane());
        //}
        frame.setVisible(true);
        frame.pack();
        this.piirra();
    }
    
    /*private void luoGraafisetKomponentit(Container c){
        this.piirtaja = new GraafinenPiirtaja();
        JPanel panel = new JPanel();
        panel.addKeyListener(new Kuuntelija(new Komentotulkki(peli, this),this));
        c.add(panel);
    }*/
    
    private void luoTekstiKomponentit(Container c){
        JTextArea tekstikentta = new JTextArea();
        Komentotulkki t = new Komentotulkki(peli, this);
        this.piirtaja = new TekstiPiirtaja(tekstikentta,100,30,this);
        Kuuntelija k = new Kuuntelija(t,this);
        tekstikentta.setFont(new Font(this.fontName,Font.PLAIN,17));
        //tekstikentta.setText("Hello World");
        tekstikentta.addKeyListener(k);
        tekstikentta.setEditable(false);
        c.add(tekstikentta);
        
    }
   
    /**
     * piirrä peli
     */
    public void piirra(){
        this.piirtaja.piirra();
        //this.piirtaja.tulosta(testString());
    }
    
    public void tulosta(String s){
        this.piirtaja.tulosta(s);
    }
    
    public Piirtaja getPiirtaja(){
        return this.piirtaja;
    }
    
    public void näytä(Näkymä näkymä){
        this.piirtaja.vaihdaNäkymää(näkymä);
    }

    public Pelilogiikka getLogiikka() {
        return this.peli;
    }

}

