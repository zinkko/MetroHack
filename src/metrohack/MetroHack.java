/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack;

import javax.swing.SwingUtilities;
import metrohack.UI.Käyttöliittymä;
import metrohack.logiikka.Pelilogiikka;
import metrohack.maailma.Tasotehdas;

public class MetroHack{
    
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
        // default to textbased UI for now
        
        Tasotehdas tehdas = new Tasotehdas();
        Pelilogiikka logiikka = new Pelilogiikka(tehdas);
        Käyttöliittymä ui = new Käyttöliittymä(logiikka, font);
        logiikka.setUI(ui);
        SwingUtilities.invokeLater(ui);
        
    }
    

}
