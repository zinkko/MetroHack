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
public class Huone {
    
    private List<Huone> viereiset;
    protected List<Tiili> osat;
    private int pituus; //huoneen pituus vaakasuunnassa
    private int leveys; //huoneen pituus pystysuunnassa
    
    public Huone(int pituus, int leveys){
        this.pituus = pituus;
        this.leveys = leveys;
    }
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
