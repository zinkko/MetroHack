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
    private List<Tiili> varatutTiilet;

    public Taso(int huoneidenMaara, boolean onkoKauppaa, List<Linja> metrot) {
        this.name = "Ankkalan metro";
        //this.metrot = new LinkedList<>();
        this.metrot = metrot;
        this.huoneet = new ArrayList<>();
        this.hahmot = new ArrayList<>();
        this.tiilet = new ArrayList<>();
        this.varatutTiilet = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 30; j++) {
                tiilet.add(new Tiili(i, j, Tiilityyppi.NOLLA));
            }
        }
        
        luoHuoneita();
        //luoTaso(huoneidenMaara, onkoKauppaa);
    }
    
    public Taso(List<Linja> metrot){
        this.name = "Ankkalan Metro";
        this.metrot = metrot;
        this.hahmot = new ArrayList<>();
        this.huoneet = new ArrayList<>();
        this.tiilet = new ArrayList<>();
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 30; j++) {
                tiilet.add(new Tiili(i, j, Tiilityyppi.NOLLA));
            }
        }
    }
    
    public List<Tiili> getTiilet(){
        return this.tiilet;
    }
    
    public void lisaaHuone(Huone h){
        this.huoneet.add(h);
    }
    
    public boolean collide(int x, int y, int pituus, int leveys){
        
        for (Huone huone : this.huoneet){
            boolean x1 = huone.getX()<x && x<huone.getX()+huone.getPituus();
            boolean x2 = x<huone.getX() && huone.getX() < x + pituus;
            if (!(x1 || x2)){
                continue;
            }
            boolean y1 = huone.getY() < y && y < huone.getY() + huone.getLeveys();
            boolean y2 = y < huone.getY() && huone.getY() < y + leveys;
            
            if (y1 || y2){
                return true;
            }
            
        }
        return false;
    }
    
    public List<Huone> huoneet(){
        return this.huoneet;
    }

    private void luoHuoneita() {
        Huone h = new Huone(this.tiilet,8,4,20,10);
        this.huoneet.add(h);
        //this.huoneet.add(new Huone(this.tiilet,10,5,5,17));
    }
    
    public Tiilityyppi getTiili(int x, int y){ 
        
        for (Tiili t:this.tiilet){
            if (t.getX()==x && t.getY()==y){
                return t.getTyyppi();             
            }
        }
        return null;
    }
    
    public List<Linja> getMetroLinjat() {
        return this.metrot;
    }

    public void piirra(char[][] map) {
        for (Huone h : huoneet) {
            h.piirra(map);
        }
        /*for (Hahmo h : hahmot) {
            h.piirra(map); // hahmot piirretään päälle
        }*/
    }
    
    public void lisaaHahmo(Hahmo h){
        this.hahmot.add(h);
    }
    
    public List<Hahmo> getHahmot(){
        return this.hahmot;
    }

}
