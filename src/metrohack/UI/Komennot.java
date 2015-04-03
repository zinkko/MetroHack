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
    
    private static Map<String, Consumer<Pelaaja>> komennot = new HashMap<>();
    
    static {
        setup();
    }
    
    private static void setup(){
        alustaLiikkumisKomennot();
    }
    
    private static void alustaLiikkumisKomennot(){
        Stream.of(Suunta.values()).forEach( (Suunta s) -> 
                komennot.put(s.merkkijono(), liiku(s)));
    }

    public static Consumer<Pelaaja> hae(String komento) {
        if (!komennot.containsKey(komento)) return ohjeet();
        return komennot.get(komento);
    }
    
    private static Consumer<Pelaaja> liiku(Suunta suunta) {
        return pelaaja -> {
            pelaaja.liiku(suunta.getDx(), suunta.getDy());
        };
    }
    
    private static Consumer<Pelaaja> ohjeet(){
        return pelaaja -> {
            System.out.println("nämä ovat ohjeet");
        };
    }
    
}
