/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.List;

/**
 *
 * @author ilari
 */
public class Taso {
    private String name;
    private List<Huone> huoneet;
    private List<Hahmo> hahmot;
    
    public Taso(){
        this.name = "Ankkalan metro";
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
