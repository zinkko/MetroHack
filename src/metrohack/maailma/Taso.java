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
    
    public Taso(int huoneidenMaara, boolean onkoKauppaa, List<Linja> metrot){
        this.name = "Ankkalan metro";
        //this.metrot = new LinkedList<>();
        this.metrot = metrot;
        this.huoneet = new ArrayList<>();
        this.hahmot = new ArrayList<>();
        luoTaso(huoneidenMaara, onkoKauppaa);
    }
    
    private void luoTaso(int huoneidenMaara, boolean onkoKauppaa){ //huom, huoneiden määrän lisäksi tulee metrolaiturit
        Random r = new Random();
        double millainenHuone;
        int huoneenKoko;
        int huoneenLeveys;
        
        for (int i = 0; i<metrot.size(); i++){ //tämä looppi luo metrolaiturit
            huoneet.add(new Metrolaituri (metrot.get(i), 5, 10));
            }
        
     
        for (int i = 0; i<huoneidenMaara; i++){ //tämä looppi luo normihuoneet
            millainenHuone = r.nextDouble();
            huoneenKoko = r.nextInt(45)+3;
            
            if (millainenHuone < 0.25){          //tällöin luo pystykäytävä
                huoneet.add(new Huone(1, huoneenKoko));
            } else if (millainenHuone < 0.50){  // tällöin luo vaakasuuntainen käytävä
                huoneet.add(new Huone(huoneenKoko, 1));
            } else {
                huoneenLeveys = r.nextInt(45)+3; // muutoin luo tavallisen muotoinen huone
                huoneet.add(new Huone (huoneenKoko, huoneenLeveys));
            }
        }
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
