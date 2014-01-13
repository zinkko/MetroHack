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
    private int leveys; //huoneen pituus vaakasuunnassa
    private int pituus; //huoneen pituus pystysuunnassa
    private int x, y;

    public Huone(List<Tiili> kaikki, int leveys, int pituus, int x, int y) {
        this.x = x;
        this.y = y;
        this.leveys = leveys;
        this.pituus = pituus;
        this.seinatiilet = new ArrayList<>();
        this.osat = valitseTiiletHuoneeseen(kaikki);
        this.viereiset = new LinkedList<>();

        this.setSeinatiilet();
    }

    @Override
    public String toString(){
        return "("+this.x+","+this.y+") "+this.leveys+"x"+this.pituus;
    }
    
    private List<Tiili> valitseTiiletHuoneeseen(List<Tiili> kaikki) {
        List<Tiili> huoneenTiilet = new ArrayList<>();

        for (Tiili t : kaikki) {
            if (t.getX() >= this.x && t.getX() < this.x + this.leveys
                    && t.getY() >= this.y && t.getY() < this.y + this.pituus) {
                t.setTyyppi(Tiilityyppi.LATTIA);
                if (t.getX()==x || t.getY()==y|| t.getX() == x+leveys-1 || t.getY()==y+pituus-1){
                    t.setTyyppi(Tiilityyppi.SEINA);
                }
                huoneenTiilet.add(t);
            }
        }

        return huoneenTiilet;
    }

    /*
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
     }*/
    public int MillaSeinallaTiiliOn(int x, int y) {
        // 1=P, 2=I, 3=E, 4=L ja nolla virhe
        for (Tiili t : seinatiilet) {
            if (t.getX() == x && t.getX() == y) { //nyt löytyi metodille annettu tiili
                if (x == this.x) {
                    return 4;
                } else if (y == this.y) {
                    return 1;
                } else if (y == this.y + this.pituus - 1) {
                    return 3;
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }

    public List<Tiili> getSeinatiilet() {    //teknisesti ottaen palauttaa seinät ilman nurkkia
        List<Tiili> seinatIlmanNurkkia = new ArrayList<>();

        for (Tiili t : this.seinatiilet) {

            if (t.getX() == this.x || t.getX() == this.x + this.leveys) {
                if (t.getY() == this.y || t.getY() == this.y + this.pituus) {
                    continue; // nurkka
                }
            }
            seinatIlmanNurkkia.add(t);
        }

        return seinatIlmanNurkkia;
    }

    private void setSeinatiilet() {
        for (Tiili t : this.osat) {
            if (t.getTyyppi() == Tiilityyppi.LATTIA) {
                continue;
            }

            this.seinatiilet.add(t);
        }

    }

    public int[] getSijainti() {
        return new int[]{x, y};
    }

    public boolean onkoPaallekkain(int[] sijainti) {
        for (Tiili t : osat) {
            if (t.getSijainti() == sijainti) {
                return true;
            }
        }
        return false;
    }
    
    public boolean doesItCollide(Huone annettu){
        for (Tiili huoneenTiili: this.osat){
            for (Tiili annetunHuoneenTiili: annettu.getOsat()){
                if (huoneenTiili.getSijainti()==annetunHuoneenTiili.getSijainti()){
                    return true;
                }
            }
        }
        return false;
    }

    public List<Tiili> getOsat() {
        return this.osat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPituus() {
        return leveys;
    }

    public int getLeveys() {
        return pituus;
    }

    public void piirra(char[][] map) {
        for (Tiili t : osat) {
            t.piirra(map);
        }
    }
}
