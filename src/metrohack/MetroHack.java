/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack;

import javax.swing.SwingUtilities;
import metrohack.UI.UserInterface;
import metrohack.maailma.Taso;


/**
 *
 * @author ilari
 */
public class MetroHack{

    private UserInterface ui;
    
    public MetroHack(boolean textBased){
        this.ui = new UserInterface(this,textBased);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetroHack peli = new MetroHack(true);
        SwingUtilities.invokeLater(peli.ui);
    }
    
    public Taso getCurrentLevel(){
        return null;
    }
    
}
