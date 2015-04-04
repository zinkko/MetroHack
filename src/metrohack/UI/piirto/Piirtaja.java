/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI.piirto;

import metrohack.maailma.entities.Reppu;

/**
 *
 * @author ilari
 */
public interface Piirtaja {
    
    public void piirra();
    public void setPitkaKomento(String komento);
    public void tulosta(String tuloste);
    public void toggleInventory(); // translation pending

    public void piirräReppu(Reppu reppu);

    public void piirraKartta(int x, int y);
}
