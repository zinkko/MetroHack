/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

/**
 *
 * @author Kulmala
 */
import static metrohack.maailma.entities.TavaranTyyppi.*;
import java.util.Random;

public class Tavara {

    protected String nimi;
    protected String kuvaus;
    protected TavaranTyyppi tyyppi;
    protected int lukuarvo; //esim. ruuilla: montako plussaa saa tämän syömällä
    protected int hinta;
    protected Random random;

    public Tavara(String nimi, int hinta) {
        this.nimi = nimi;
        this.lukuarvo = 0;
        this.random = new Random();

        if (nimi.equals("kertalippu")) {
            this.kuvaus = "Kertalipulla voit matkustaa yhdellÃ¤ metrolinjalla kerran.";
            this.tyyppi = LIPPU;
            this.lukuarvo = 0;
        } else if (nimi.equals("kausilippu")) {
            this.kuvaus = "Kausilipulla voit matkustaa ilmaiseksi niin kauan kuin se on voimassa (eli hyvin kauan)";
            this.tyyppi = LIPPU;
            this.lukuarvo = 0;
        } else if (nimi.equals("patonki")) {
            this.kuvaus = "Herkullista ruokaa, muttei kovin tÃ¤yttÃ¤vÃ¤Ã¤.";
            this.tyyppi = RUOKA;
            this.lukuarvo = 3;
        } else if (nimi.equals("kebab-ateria")) {
            this.kuvaus = "Ihana ja tÃ¤yttÃ¤vÃ¤ herkku.";
            this.tyyppi = RUOKA;
            this.lukuarvo = 5;
        } else if (nimi.equals("muffini")) {
            this.kuvaus = "Pieni mukava vÃ¤lipala.";
            this.tyyppi = RUOKA;
            this.lukuarvo = 2;
        } else if (nimi.equals("omena")) {
            this.kuvaus = "Kelvollinen vÃ¤lipala.";
            this.tyyppi = RUOKA;
            this.lukuarvo = 2;
        } else if (nimi.equals("korvatulpat")) {
            this.kuvaus = "Usein yllÃ¤ttÃ¤vÃ¤n hyÃ¶dylliseksi paljastuva pikku esine.";
            this.tyyppi = SUOJA;
            this.lukuarvo = 5;
        } else if (nimi.equals("rahakasa")) {
            this.kuvaus = "Jonkun unohtamat pari kolikkoa tai ehkÃ¤ jopa seteli";
            this.tyyppi = MUU;
            this.lukuarvo = random.nextInt(20) + 1;
        } else {
            this.kuvaus = "ei kuvausta";
            this.tyyppi = MUU;
            this.lukuarvo = 0;
        }
        this.hinta = hinta;
    }

    public String getNimi() {
        return this.nimi;
    }

    public int getHinta() {
        return this.hinta;
    }

    public String getKuvaus() {
        return this.nimi + "\n" + this.kuvaus;
    }

    public TavaranTyyppi getTyyppi() {
        return this.tyyppi;
    }
}
