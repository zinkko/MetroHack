/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ilari
 */
public class Linja {
    private List<Taso> asemat;
    
    public Linja(){
        this.asemat = new LinkedList<>();
    }
    
    public void lisaaAsema(Taso asema){
        this.asemat.add(asema);
    }
    
    public boolean onYhteys(Taso eka, Taso toka){
        return this.asemat.contains(eka) && this.asemat.contains(toka);
    }
    
    public List<Linja> getRisteavatLinjat(){
        ArrayList<Linja> ret = new ArrayList<>();
        for (Taso asema : this.asemat){
            for (Linja linja: asema.getMetroLinjat()){
                if (linja.equals(this)){
                    continue;
                }
                ret.add(linja);
            }
        }
        return ret;
    }
}
