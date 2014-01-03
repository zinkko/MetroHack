/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ilari
 */
public class Metrokartta {
    private Map<Taso,List<Linja>> yhteydet;
    private List<Linja> metroLinjat;
    
    public Metrokartta(){
        this.yhteydet = new HashMap<>();
        this.metroLinjat = new ArrayList<>();
    }
    
    public void lisaaAsema(Taso asema,List<Linja> metrot){
        this.yhteydet.put(asema, metrot);
    }
    
    public void lisaaLinja(Linja metro, List<Taso> pysakit){
        for (Taso asema:pysakit){
            if (this.yhteydet.containsKey(asema)){
                this.yhteydet.get(asema).add(metro);
            }else{
                LinkedList<Linja> linjat = new LinkedList<>();
                linjat.add(metro);
                this.yhteydet.put(asema, linjat);
            }
        }
    }
}
