/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import static metrohack.UI.Näkymä.*;
import metrohack.UI.piirto.Piirtaja;
import metrohack.logiikka.Pelilogiikka;
import metrohack.logiikka.Suunta;

/**
 *
 * @author ilari
 */
public class Komennot {
    
    private final Map<String, Consumer<Pelilogiikka>> peliKomennot;
    private final Map<Näkymä, Consumer<Piirtaja>> piirtoKomennot;
    private final Käyttöliittymä ui;
    
    public Komennot(Käyttöliittymä ui) {
        peliKomennot = new HashMap<>();
        piirtoKomennot = new HashMap<>();
        this.ui = ui;
        luoKomennot();
    }
    
    private void luoKomennot(){
        peliKomennot();
        piirtoKomennot();
    }
    
    private void peliKomennot(){
        alustaLiikkumisKomennot();
        peliKomennot.put("i", näytäTavarat());
        peliKomennot.put("m", näytäKartta());
    }
    
    private void piirtoKomennot(){
        
    }
    
    private void alustaLiikkumisKomennot(){
        Stream.of(Suunta.values()).forEach( (Suunta s) -> 
                peliKomennot.put(s.merkkijono(), liiku(s)));
    }

    public Consumer<Pelilogiikka> hae(String komento) {
        if (!peliKomennot.containsKey(komento)) return ohjeet();
        return peliKomennot.get(komento);
    }
    
    private Consumer<Pelilogiikka> näytäTavarat() {
        return logic -> {
            logic.getUI().näytä(REPPU);
        };
    }
    
    private Consumer<Pelilogiikka> näytäKartta() {
        return logic -> {
            logic.getUI().näytä(KARTTA);
        };
    }
    
    private Consumer<Pelilogiikka> liiku(Suunta suunta) {
        return logiikka -> {
            logiikka.getPelaaja().liiku(suunta.getDx(), suunta.getDy());
            logiikka.vuoro();
            logiikka.getUI().näytä(PERUS);
        };
    }
    
    private Consumer<Pelilogiikka> ohjeet(){
        return logiikka -> {
            logiikka.getUI().näytä(OHJEET);
        };
    }
    
}
