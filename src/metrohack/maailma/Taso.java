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
        
        for (int i = 0; i<35; i++){
            for (int j = 0; j<25; j++){
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
            sijaintitaulukko = sijoitaHuone(i, 4, 4);
            huoneet.add(new Metrolaituri (metrot.get(i), 5, 10, sijaintitaulukko[0], sijaintitaulukko[1]));
        }

        for (int i = 0; i<huoneidenMaara; i++){ //tämä looppi luo normihuoneet
            millainenHuone = r.nextDouble();
            huoneenPituus = r.nextInt(15)+3;
            
            if (millainenHuone < 0.25){          //tällöin luo pystykäytävä
                huoneenLeveys = huoneenPituus;
                huoneenPituus = 3;
            } else if (millainenHuone < 0.50){  // tällöin luo vaakasuuntainen käytävä
                huoneenLeveys = 3;
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
        while (true){
            int x = r.nextInt(20)+5; //tiilien määrä
            int y = r.nextInt(35)+5;
            int z = r.nextInt(8)-3;
            //Huone h = huoneet.get(monesko-1);
            
            if (monesko==0){    //jos kyseessä eka huone, sijoita randomilla, tätäkin pitänee kyllä parannella
                sijainti[0] = x;
                sijainti[1] = y;
            } else {
                Huone h = huoneet.get(r.nextInt(huoneet.size()));
                
                
                List<Tiili> listaOvenPaikoista = h.getSeinatiilet();
                x = r.nextInt(listaOvenPaikoista.size()); //arpoo, minne ovi koitetaan törkätä
                Tiili ovenpaikka = h.getSeinatiilet().get(x); //nyt pitäis koittaa kaivaa tiili, johon ovea laitetaan
                int ilmansuunta = h.MillaSeinallaTiiliOn(ovenpaikka.getX(), ovenpaikka.getY());
            
                if (ilmansuunta == 1){
                    sijainti[0] = h.getX()-pituus;
                    sijainti[1] = h.getY()-z;
                } else if (ilmansuunta == 2){
                    sijainti[0] = h.getX() - 1;
                    sijainti[1] = h.getY() + z;
                } else if (ilmansuunta == 3){
                    sijainti[0] = h.getX() + z;
                    sijainti[1] = h.getY()+h.getLeveys()+1;
                } else {
                    sijainti[0] = h.getX() + z;
                    sijainti[1] = h.getY()+h.getLeveys()-1;
                }
                ovenpaikka.setTyyppi(Tiilityyppi.OVI); //ei toimi koska kuunatsit?
                
            }

            boolean feilaakoHuoneenSijoitus = false;
            for (Huone huone: huoneet){
                for (int i=x; i<x+pituus;i++){
                    for (int j=y; j<y+pituus;j++){
                        int[] taulukko = new int[2];
                        taulukko[0]=i;
                        taulukko[1]=j;
                        if (huone.onkoPaallekkain(taulukko)){
                            feilaakoHuoneenSijoitus = true;
                            break;
                        }
                    }
                }
            }

            if (!feilaakoHuoneenSijoitus){
                
                break;
            }
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
