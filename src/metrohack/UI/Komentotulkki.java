/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import java.util.function.Consumer;
import metrohack.logiikka.Pelilogiikka;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Komentotulkki {
    
    private final Pelilogiikka logiikka;
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
        Consumer<Pelaaja> con = Komennot.hae(komento);
        con.accept(logiikka.getPelaaja());
        this.logiikka.piirra();
    }
}
