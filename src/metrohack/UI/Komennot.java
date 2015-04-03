/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Komennot {
    
    private static Map<String, Consumer<Pelaaja>> komennot = new HashMap<>();

    public static Consumer<Pelaaja> hae(String komento) {
        if (!komennot.containsKey(komento)) return ohjeet();
        return komennot.get(komento);
    }
    
    private static Consumer<Pelaaja> liiku() {
        return pelaaja -> {
            pelaaja.liiku(1, 2);
        };
    }
    
    private static Consumer<Pelaaja> ohjeet(){
        return pelaaja -> {
            System.out.println("nämä ovat ohjeet");
        };
    }
    
}
