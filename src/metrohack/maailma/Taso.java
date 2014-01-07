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
    
    public Taso(int huoneidenMaara, boolean onkoKauppaa, List<Linja> metrot){
        this.name = "Ankkalan metro";
        //this.metrot = new LinkedList<>();
        this.metrot = metrot;
        this.huoneet = new ArrayList<>();
        this.hahmot = new ArrayList<>();
        this.tiilet = new ArrayList<>();
        
        for (int i = 0; i<50; i++){
            for (int j = 0; j<80; j++){
                tiilet.add(new Tiili(i,j, Tiilityyppi.NOLLA));              
            }
        }
        
        luoTaso(huoneidenMaara, onkoKauppaa);
    }
    
    private void luoTaso(int huoneidenMaara, boolean onkoKauppaa){ //huom, huoneiden määrän lisäksi tulee metrolaiturit
        Random r = new Random();
        double millainenHuone;
        int huoneenPituus;
        int huoneenLeveys = 1;
        int[] sijaintitaulukko;
        
        for (int i = 0; i<metrot.size(); i++){ //tämä looppi luo metrolaiturit
            sijaintitaulukko = sijoitaHuone(i, 5, 10);
            huoneet.add(new Metrolaituri (metrot.get(i), 5, 10, sijaintitaulukko[0], sijaintitaulukko[1]));
        }

        for (int i = 0; i<huoneidenMaara; i++){ //tämä looppi luo normihuoneet
            millainenHuone = r.nextDouble();
            huoneenPituus = r.nextInt(15)+3;
            
            if (millainenHuone < 0.25){          //tällöin luo pystykäytävä
                huoneenLeveys = huoneenPituus;
                huoneenPituus = 1;
            } else if (millainenHuone < 0.50){  // tällöin luo vaakasuuntainen käytävä
                huoneenLeveys = 1;
            } else {
                huoneenLeveys = r.nextInt(15)+3; // muutoin luo tavallisen muotoinen huone
                if (onkoKauppaa){
                    huoneet.add(new Kauppa (huoneenPituus, huoneenLeveys,0,0));
                    onkoKauppaa = false;
                    continue;
                }
            }
            sijaintitaulukko = sijoitaHuone((i+metrot.size()), huoneenPituus, huoneenLeveys);
            huoneet.add(new Huone(huoneenPituus, huoneenLeveys, sijaintitaulukko[0], sijaintitaulukko[1]));
        }

    }
    
    public int[] sijoitaHuone(int monesko, int pituus, int leveys){
        int[] sijainti = new int[2];
        Random r = new Random();
        int x = r.nextInt(50); //tiilien määrä
        int y = r.nextInt(80);
        if (monesko==0){    //jos kyseessä eka huone, sijoita randomilla
            sijainti[0] = x;
            sijainti[1] = y;
        } else {
            
        }
        return sijainti;
    }
    
    public List<Linja> getMetroLinjat(){
        return this.metrot;
    }
    
    public void piirra(char[][] map){
        for (Huone h:huoneet){
            h.piirra(map);
        }
        for (Hahmo h:hahmot){
            h.piirra(map); // hahmot piirretään päälle
        }
    }
    
   
}
