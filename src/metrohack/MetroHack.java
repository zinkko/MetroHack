/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack;

import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingUtilities;
import metrohack.UI.Piirtaja;
import metrohack.UI.UserInterface;
import metrohack.maailma.Linja;
import metrohack.maailma.Taso;
import metrohack.maailma.TasoGeneraattori;
import metrohack.maailma.Tiilityyppi;


/**
 *
 * @author ilari
 */
import metrohack.maailma.entities.Pelaaja;

public class MetroHack{

    private UserInterface ui;
    private Pelaaja pelaaja;
    private Taso tasoNyt;
    
    public MetroHack(){
        //this.ui = new UserInterface(this,textBased);
        this.pelaaja = new Pelaaja(this, 20, "Pentti", 15, 25);
        //this.luoEkaTaso();
        this.tasoNyt = (new TasoGeneraattori()).luoTaso();
        this.pelaaja.vaihdaTasoa(tasoNyt);
        
    }
    
    public void setWalkThruWalls(boolean b){
        this.pelaaja.setTestMode(b);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String font;
        String osName = System.getProperty("os.name");
        if (osName.equals("Linux")){
            font = "Courier";
        }else{
            font = "Consolas";
        }
        // default t textbased UI for now
        MetroHack peli = new MetroHack();
        peli.ui = new UserInterface(peli,font);
        SwingUtilities.invokeLater(peli.ui);
    }
    

    private void luoEkaTaso(){ // korvaa jollain myöhemmin
        List<Linja> metrot = new LinkedList<>();
        Linja eka = new Linja();
        Linja toka = new Linja();
        metrot.add(eka);
        metrot.add(toka);
        Taso ekaTaso = new Taso(3,false,metrot);
        this.tasoNyt = ekaTaso;
    }

    public void tulosta(String s){
        this.ui.tulosta(s);
    }
    
    private void vuoro(){
        
        this.ui.piirra();
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
    
    public Piirtaja getPiirtaja(){
        return this.ui.getPiirtaja();
    }
}
