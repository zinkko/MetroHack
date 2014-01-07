/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.LinkedList;
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
    private int x,y;
    
    public Huone(int pituus, int leveys,int x,int y){
        this.x=x;
        this.y=y;
        this.pituus = pituus;
        this.leveys = leveys;
        this.osat = luoTyhjaHuone(pituus,leveys);
        this.viereiset = new LinkedList<>();
    }
    
    public Huone(List<Tiili> huone,int p, int l,int x, int y){
        this.pituus = p;
        this.leveys = l;
        this.x = x;
        this.y = y;
        this.osat = huone;
    }
    
    private List<Tiili> luoTyhjaHuone(int pituus, int leveys){
        List<Tiili> ret = new LinkedList<>();
        Tiilityyppi nxt;
        for (int i=this.x;i<this.x+this.pituus;i++){
            for (int j=this.y;j<this.y+this.leveys;j++){
                if (i==this.x || j==this.y || i==this.x+this.pituus-1
                        || j==this.y+this.leveys-1){
                    nxt = Tiilityyppi.SEINA;
                }
                else{
                    nxt = Tiilityyppi.LATTIA;
                }
                ret.add(new Tiili(i,j,nxt));
            }
        }
        
        return ret;
    }
    
    
    
    public int[] getSijainti(){
        return new int[]{x, y};
    }
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
