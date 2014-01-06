/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.MetroHack;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author ilari
 */
public class UserInterface implements Runnable{
    
    private final JFrame frame;
    private final boolean isTextBased;
    private final MetroHack peli;
    private Piirtaja piirtaja;
    
    public UserInterface(MetroHack peli, boolean isTextBased){
        this.frame = new JFrame();
        this.isTextBased = isTextBased;
        this.peli = peli;     
    }
    
    @Override
    public void run(){
        frame.setPreferredSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        if (this.isTextBased){
            luoTekstiKomponentit(frame.getContentPane());
        }else{
            luoGraafisetKomponentit(frame.getContentPane());
        }
        frame.setVisible(true);
        frame.pack();
    }
    
    private void luoGraafisetKomponentit(Container c){
        System.out.println("Hello World!");
        JPanel panel = new JPanel();
        panel.addKeyListener(new Kuuntelija(new KomentoTulkki(peli)));
        c.add(panel);
    }
    
    private void luoTekstiKomponentit(Container c){
        System.out.println("Hello World!");
        JTextArea tekstikentta = new JTextArea();
        KomentoTulkki t = new KomentoTulkki(peli);
        Kuuntelija k = new Kuuntelija(t);
        tekstikentta.setFont(new Font("Courier",Font.PLAIN,17));
        tekstikentta.setText("Hello World");
        tekstikentta.addKeyListener(k);
        tekstikentta.setEditable(false);
        c.add(tekstikentta);
        this.piirtaja = new Piirtaja(tekstikentta,100,100);
    }
   
    /**
     * piirrä peli
     */
    public void piirra(){
        this.piirtaja.piirra();
    }
}

