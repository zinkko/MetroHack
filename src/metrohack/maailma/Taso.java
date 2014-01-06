/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import metrohack.maailma.entities.Hahmo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ilari
 */
public class Taso {
    private String name;
    private List<Huone> huoneet;
    private List<Hahmo> hahmot;
    private List<Linja> metrot;
    
    public Taso(int huoneidenMaara){
        this.name = "Ankkalan metro";
        this.metrot = new LinkedList<>();
        luoTaso(huoneidenMaara);
    }
    
    private void luoTaso(int huoneidenMaara){
        
    }
    
    public List<Linja> getMetroLinjat(){
        return this.metrot;
    }
    
    public List<Hahmo> getHahmot(){
        return this.hahmot;
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
