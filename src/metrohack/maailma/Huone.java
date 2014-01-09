/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.*;

/**
 *
 * @author ilari
 */
public class Huone {
    
    private List<Huone> viereiset;
    protected List<Tiili> osat;
    protected List<Tiili> seinatiilet;
    private int pituus; //huoneen pituus vaakasuunnassa
    private int leveys; //huoneen pituus pystysuunnassa
    private int x,y;
    
    public Huone(int pituus, int leveys,int x,int y){
        this.x=x;
        this.y=y;
        this.pituus = pituus;
        this.leveys = leveys;
        this.seinatiilet = new ArrayList<>();
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
                    if (i==this.x && j==this.y || i==this.x+this.pituus-1
                        && j==this.y+this.leveys-1 ||i==this.x && j ==this.leveys-1 ||i==this.pituus-1 && j==this.y){
                        //tämä on nurkka, ignore
                    } else {
                        seinatiilet.add(new Tiili(i,j,nxt)); //lisätään seinätiililistaan, jota tarvitaan toisaalla
                    }
                }
                else{
                    nxt = Tiilityyppi.LATTIA;
                }
                ret.add(new Tiili(i,j,nxt));
            }
        }
        
        return ret;
    }
    
    public int MillaSeinallaTiiliOn(int x, int y){
        // 1=P, 2=I, 3=E, 4=L ja nolla virhe
        for (Tiili t: seinatiilet){
            if (t.getX()==x && t.getX()==y){ //nyt löytyi metodille annettu tiili
                if (x==this.x){
                    return 4;
                } else if (y==this.y){
                    return 1;
                } else if (y==this.y+this.pituus-1){
                    return 3;
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }
    
    public List<Tiili> getSeinatiilet(){    //teknisesti ottaen palauttaa seinät ilman nurkkia
        /*List<Tiili> seinatIlmanNurkkia = new ArrayList<>();
        
        for (int i=this.x;i<this.x+this.pituus;i++){
            for (int j=this.y;j<this.y+this.leveys;j++){
                if (i==this.x && j==this.y || i==this.x+this.pituus-1
                        && j==this.y+this.leveys-1 ||i==this.x && j ==this.leveys-1 ||i==this.pituus-1 && j==this.y){
                    //tämä on nurkka, ignore
                }
                else if (i==this.x || j==this.y || i==this.x+this.pituus-1
                        || j==this.y+this.leveys-1){
                    seinatIlmanNurkkia.add( );
                    
                }
            }
        }
        */
        return seinatiilet;
    }
    
    
    
    public int[] getSijainti(){
        return new int[]{x, y};
    }
    
    public boolean onkoPaallekkain(int[] sijainti){
        for (Tiili t: osat){
            if (t.getSijainti()==sijainti){
                return true;
            }
        }
        return false;
    }
    
    public List<Tiili> getOsat(){
        return this.osat;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getPituus(){
        return pituus;
    }
    
    public int getLeveys(){
        return leveys;
    }
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
