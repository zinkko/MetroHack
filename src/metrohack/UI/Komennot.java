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
import metrohack.logiikka.Suunta;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Komennot {
    
    private final Map<String, Consumer<Pelaaja>> komennot;
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

    public Consumer<Pelaaja> hae(String komento) {
        if (!komennot.containsKey(komento)) return ohjeet();
        return komennot.get(komento);
    }
    
    private Consumer<Pelaaja> näytäTavarat() {
        return pelaaja -> {
            ui.piirräReppu(pelaaja);
        };
    }
    
    private Consumer<Pelaaja> näytäKartta() {
        return pelaaja -> {
            ui.piirräKartta(pelaaja);
        };
    }
    
    private Consumer<Pelaaja> liiku(Suunta suunta) {
        return pelaaja -> {
            pelaaja.liiku(suunta.getDx(), suunta.getDy());
        };
    }
    
    private Consumer<Pelaaja> ohjeet(){
        return pelaaja -> {
            System.out.println("nämä ovat ohjeet");
        };
    }
    
}
