/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.logiikka;

import java.util.ArrayList;
import java.util.List;
import metrohack.UI.piirto.Piirtaja;
import metrohack.UI.UserInterface;
import metrohack.maailma.Linja;
import metrohack.maailma.Taso;
import metrohack.maailma.Tasotehdas;
import metrohack.maailma.Tiilityyppi;
import metrohack.maailma.entities.Hahmo;
import metrohack.maailma.entities.Monsteri;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Pelilogiikka {
    
    private UserInterface ui;
    private final List<Taso> tasot;
    private final List<Linja> metrolinjat;
    private Pelaaja pelaaja;
    private final Tasotehdas tehdas;
    private Taso tasoNyt;
    private int moneskoVuoro;
    
    public Pelilogiikka(Tasotehdas tehdas){
        this.tasot = new ArrayList<>();
        this.tehdas = tehdas;
        this.metrolinjat = new ArrayList<>();
        this.moneskoVuoro = 0;
        alustaMaailma();
    }
    
    private void alustaMaailma(){
        this.pelaaja = new Pelaaja(20, "ilpo", 15, 25);
        tasoNyt = tehdas.luoTaso();
        
        pelaaja.asetaTaso(tasoNyt);
    }
    
    public void setUI(UserInterface ui){
        this.ui = ui;
    }
    
    public void vuoro(){
        moneskoVuoro++;
        for (Hahmo h: this.tasoNyt.getHahmot()){
            if (h.getClass() == Monsteri.class){
                ((Monsteri) h).liiku();
                if (h.getX() == pelaaja.getX() && h.getY() == pelaaja.getY()){
                    pelaaja.muutaVitutusta(1);
                }
                for (Hahmo muu: this.tasoNyt.getHahmot()) {
                    if (h.getX() == muu.getX() && h.getY() == muu.getY() && h != muu) {
                        ((Monsteri) h).peruLiike();
                    }
                }
                
            }
        }
        
        onkoPelaajaLaiturilla();
    }
    
    private void onkoPelaajaLaiturilla(){
        
    }
    
    public void tulosta(String s){
        this.ui.tulosta(s);
    }
    
    public Tiilityyppi getTiili(int x, int y){
        return this.tasoNyt.getTiili(x, y);
    }
    
    public Pelaaja getPelaaja(){
        return this.pelaaja;
    }
    
    public Taso getCurrentLevel(){
        return tasoNyt;
    }
    
    public void piirra(){
        this.ui.piirra();
    }
    
    public Taso getTaso(){
        return this.tasoNyt;
    }
    
    public int getMoneskoVuoro() {
        return this.moneskoVuoro;
    }
    
    public Piirtaja getPiirtaja(){
        return this.ui.getPiirtaja();
    }

    public UserInterface getUI() {
        return this.ui;
    }
}
