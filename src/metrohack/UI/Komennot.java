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
import metrohack.logiikka.Pelilogiikka;
import metrohack.logiikka.Suunta;

/**
 *
 * @author ilari
 */
public class Komennot {
    
    private final Map<String, Consumer<Pelilogiikka>> komennot;
    private final UserInterface ui;
    
    public Komennot(UserInterface ui) {
        komennot = new HashMap<>();
        this.ui = ui;
        setup();
    }
    
    private void setup(){
        alustaLiikkumisKomennot();
        komennot.put("i", näytäTavarat());
        komennot.put("m", näytäKartta());
    }
    
    private void alustaLiikkumisKomennot(){
        Stream.of(Suunta.values()).forEach( (Suunta s) -> 
                komennot.put(s.merkkijono(), liiku(s)));
    }

    public Consumer<Pelilogiikka> hae(String komento) {
        if (!komennot.containsKey(komento)) return ohjeet();
        return komennot.get(komento);
    }
    
    private Consumer<Pelilogiikka> näytäTavarat() {
        return logic -> {
            logic.getUI().piirräReppu();
        };
    }
    
    private Consumer<Pelilogiikka> näytäKartta() {
        return logic -> {
            logic.getUI().piirräKartta();
        };
    }
    
    private Consumer<Pelilogiikka> liiku(Suunta suunta) {
        return logiikka -> {
            logiikka.getPelaaja().liiku(suunta.getDx(), suunta.getDy());
        };
    }
    
    private Consumer<Pelilogiikka> ohjeet(){
        return logiikka -> {
            logiikka.getUI().piirräOhjeet();
        };
    }
    
}
