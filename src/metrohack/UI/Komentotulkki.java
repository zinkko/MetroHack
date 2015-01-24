/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.UI.piirto.TekstiPiirtaja;
import metrohack.UI.piirto.Piirtaja;
import metrohack.logiikka.Pelilogiikka;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Komentotulkki {
    
    private Pelilogiikka logiikka;
    private boolean onPitkaKomento;
    private String puskuri;
    
    private static final char KOMENTO_MERKKI = '$';
    private static final String MOVE_CHARS = "qweaxdzsc";
    /*
    * q w e \ ^ /
    * a x d < + >
    * z s c / v \
    */

    public Komentotulkki(Pelilogiikka peli){
        this.logiikka = peli;
        this.puskuri = "";
        this.onPitkaKomento = false;
    }

    /**
     * näppiksen kuuntelija kutsuu tätä metodia
     * @param komento painettu merkki
     */
    public void otaVastaanKomento(char komento){
        
        if (this.onPitkaKomento) {
            if (komento == '\n'){
                otaVastaanKomento(this.puskuri);
                this.onPitkaKomento = false;
                this.puskuri = "";
            }else if (komento == '\b' && this.puskuri.length()!=0){
                this.puskuri = this.puskuri.substring(0, this.puskuri.length()-1);
            }else{
                puskuri = puskuri + komento;
            }
            this.logiikka.getPiirtaja().setPitkaKomento(puskuri);
            return;
        }

        if (komento == KOMENTO_MERKKI){
            this.onPitkaKomento = true;
        }else{
            otaVastaanKomento(""+komento);
        }
    }
    
    private void otaVastaanKomento(String komento){
        
        this.logiikka.piirra();   
    }
    
    private void inventoryCommands(String cmd){
        
    }
    
    private void peliKomennot(String komento){
        if (komento.length()==1 && MOVE_CHARS.contains(komento)){
            liiku(komento);
            logiikka.piirra();
            return;
        }
        switch (komento){
            case "i":
                logiikka.getPiirtaja().toggleInventory(); break;
            default:
                System.out.println("Tuntematon komento: "+ komento);
        }        
    }
    
    
    private void liiku(String komento){
        int i = MOVE_CHARS.indexOf(komento);
        int x = i%3-1;
        int y = -1*(i/3-1);
        logiikka.getPelaaja().liiku(x, y);
        logiikka.vuoro();
    }
}
