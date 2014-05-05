/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import java.util.*;
import static metrohack.maailma.entities.TavaranTyyppi.*;

/**
 *
 * @author Kulmala
 */
public class Reppu {

    private int rahat;
    private List<Tavara> tavarat;
    private int max;

    public Reppu(int rahat, int max) {
        this.rahat = rahat;
        this.tavarat = new ArrayList<>();
        this.max = max;
    }

    public boolean onkoTavaraa(String tavaranNimi) {
        for (Tavara t : tavarat) {
            if (t.getNimi().equals(tavaranNimi)) {
                return true;
            }
        }
        return false;
    }

    public void lisaaRahaa(int lisays) {
        if (lisays > 0) {
            this.rahat += lisays;
        }
    }

    public boolean kaytaRahaa(int otto) {
        if (rahat - otto >= 0 && otto > 0) {
            rahat -= otto;
            return true;
        }
        return false;
    }

    public boolean lisaaTavara(Tavara tavara) {
        if (tavarat.size() < max) {
            tavarat.add(tavara);
            return true;
        }
        return false;
    }

    public String repunSisalto() {
        String palautettava = "ruokaa: ";
        for (Tavara t : tavarat) {
            if (t.getTyyppi() == RUOKA || t.getTyyppi() == JUOTAVA) {
                palautettava += t.nimi;
                palautettava += ", ";
            }
        }
        palautettava += "lippuja: ";
        for (Tavara t : tavarat) {
            if (t.getTyyppi() == LIPPU) {
                palautettava += t.nimi;
                palautettava += ", ";
            }
        }
        palautettava += "ja muuta: ";
        for (Tavara t : tavarat) {
            if (!(t.getTyyppi() == RUOKA || t.getTyyppi() == JUOTAVA || t.getTyyppi() == LIPPU)) {
                palautettava += t.nimi;
                palautettava += ", ";
            }
        }

        return "Repussa on " + rahat + " rahaa, " + palautettava;
    }
}
