/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack;

import java.util.LinkedList;
import javax.swing.SwingUtilities;
import metrohack.UI.UserInterface;
import metrohack.maailma.Linja;
import metrohack.maailma.Taso;


/**
 *
 * @author ilari
 */
import metrohack.maailma.entities.Pelaaja;

public class MetroHack{

    private UserInterface ui;
    private Pelaaja pelaaja;
    private Taso tasoNyt;
    
    public MetroHack(boolean textBased){
        this.ui = new UserInterface(this,textBased);
        this.pelaaja = new Pelaaja(10, "Pentti", 50,50);
        this.luoEkaTaso();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetroHack peli = new MetroHack(true);
        SwingUtilities.invokeLater(peli.ui);
    }
    

    private void luoEkaTaso(){ // korvaa jollain myöhemmin
        Taso ekaTaso = new Taso(1,true,new LinkedList<Linja>());
        tasoNyt = ekaTaso;
    }
    
    private void vuoro(){
        
        this.ui.piirra();
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
    
}
