/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma;

import metrohack.maailma.entities.Hahmo;
import java.util.*;

/**
 *
 * @author ilari
 */
public class Taso {

    private String name;
    private List<Huone> huoneet;
    private List<Hahmo> hahmot;
    private List<Linja> metrot;
    private List<Tiili> tiilet;

    public Taso(int huoneidenMaara, boolean onkoKauppaa, List<Linja> metrot) {
        this.name = "Ankkalan metro";
        //this.metrot = new LinkedList<>();
        this.metrot = metrot;
        this.huoneet = new ArrayList<>();
        this.hahmot = new ArrayList<>();
        this.tiilet = new ArrayList<>();

        for (int i = 0; i < 70; i++) {
            for (int j = 0; j < 30; j++) {
                tiilet.add(new Tiili(i, j, Tiilityyppi.NOLLA));
            }
        }
        //luoHuoneita();
        luoTaso(huoneidenMaara, onkoKauppaa);
    }

    private void luoHuoneita() {
        Huone h = new Huone(this.tiilet, 8, 4, 20, 10);
        this.huoneet.add(h);
        //this.huoneet.add(new Huone(this.tiilet,10,5,5,17));
    }

    private void luoTaso(int huoneidenMaara, boolean onkoKauppaa) { //huom, huoneiden määrän lisäksi tulee metrolaiturit
        Random r = new Random();
        double millainenHuone;
        int huoneenPituus;
        int huoneenLeveys = 1;
        int[] sijaintitaulukko;

        for (int i = 0; i < metrot.size(); i++) { //tämä looppi luo metrolaiturit
            sijaintitaulukko = sijoitaHuone(i, 6, 4);
            huoneet.add(new Metrolaituri(this.tiilet, metrot.get(i), 6, 4, sijaintitaulukko[0], sijaintitaulukko[1]));
        }

        for (int i = 0; i < huoneidenMaara; i++) { //tämä looppi luo normihuoneet
            millainenHuone = r.nextDouble();
            huoneenPituus = r.nextInt(10) + 3;

            if (millainenHuone < 0.25) {          //tällöin luo pystykäytävä
                huoneenLeveys = huoneenPituus;
                huoneenPituus = 3;
            } else if (millainenHuone < 0.50) {  // tällöin luo vaakasuuntainen käytävä
                huoneenLeveys = 3;
            } else {
                huoneenLeveys = r.nextInt(10) + 3; // muutoin luo tavallisen muotoinen huone
                if (onkoKauppaa) {
                    int[] tanneKauppa = this.sijoitaHuone(i, huoneenPituus, huoneenLeveys);
                    huoneet.add(new Kauppa(this.tiilet, huoneenPituus, huoneenLeveys, tanneKauppa[0],tanneKauppa[1]));
                    onkoKauppaa = false;
                    continue;
                }
            }
            sijaintitaulukko = sijoitaHuone((i + metrot.size()),
                    huoneenPituus, huoneenLeveys);
            huoneet.add(new Huone(this.tiilet, huoneenPituus, huoneenLeveys,
                    sijaintitaulukko[0], sijaintitaulukko[1]));
        }

    }

    public int[] sijoitaHuone(int monesko, int pituus, int leveys) {
        int[] sijainti = new int[2];
        Random r = new Random();
        while (true) {
            int x = r.nextInt(100 - leveys - 5) + 5; //tiilien määrä
            int y = r.nextInt(30 - pituus - 5) + 5;
            int z = r.nextInt(8) - 3; //mihin kohtaan huoneen seinää ovi laitetaan
            Tiili ovenpaikka = null;
            //Huone h = huoneet.get(monesko-1);

            if (monesko == 0) {    //jos kyseessä eka huone, sijoita aina vakiopaikkaan näin aluksi
                sijainti[0] = 40;
                sijainti[1] = 12;
            } else {
                Huone h = huoneet.get(r.nextInt(huoneet.size()));

                List<Tiili> listaOvenPaikoista = h.getSeinatiilet();
                if (h.getSeinatiilet().isEmpty()) {
                    System.out.println(h);
                    break;
                }
                x = r.nextInt(listaOvenPaikoista.size()); //arpoo, minne ovi koitetaan törkätä
                ovenpaikka = h.getSeinatiilet().get(x); //nyt pitäis koittaa kaivaa tiili, johon ovea laitetaan
                int ilmansuunta = h.MillaSeinallaTiiliOn(ovenpaikka.getX(), ovenpaikka.getY());

                if (ilmansuunta == 1) {
                    sijainti[0] = h.getX() - pituus;
                    sijainti[1] = h.getY() - z;
                } else if (ilmansuunta == 2) {
                    sijainti[0] = h.getX() - 1;
                    sijainti[1] = h.getY() + z;
                } else if (ilmansuunta == 3) {
                    sijainti[0] = h.getX() + z;
                    sijainti[1] = h.getY() + h.getLeveys() + 1;
                } else {
                    sijainti[0] = h.getX() + z;
                    sijainti[1] = h.getY() + h.getLeveys() - 1;
                }
                //ovenpaikka.setTyyppi(Tiilityyppi.OVI); ei voi olla tässä, muuten tulee liikaa ovia!
                
            }
            
            boolean feilaakoHuoneenSijoitus = false;
            for (Huone huone : huoneet) {
                for (int i = x; i < x + pituus; i++) {
                    for (int j = y; j < y + pituus; j++) {
                        int[] taulukko = new int[2];
                        taulukko[0] = i;
                        taulukko[1] = j;
                        if (huone.onkoPaallekkain(taulukko)) {
                            feilaakoHuoneenSijoitus = true;
                            break;
                        }
                    }
                }
            }

            if (!feilaakoHuoneenSijoitus) {
                if (!(ovenpaikka==null)){
                    ovenpaikka.setTyyppi(Tiilityyppi.OVI);
                }
                break;
            }
        }

        return sijainti;
    }

    public List<Linja> getMetroLinjat() {
        return this.metrot;
    }

    public void piirra(char[][] map) {
        for (Huone h : huoneet) {
            h.piirra(map);
        }
        for (Hahmo h : hahmot) {
            h.piirra(map); // hahmot piirretään päälle
        }
    }

}
