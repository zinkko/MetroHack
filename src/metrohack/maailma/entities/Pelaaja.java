/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import java.util.ArrayList;
import metrohack.maailma.Tiilityyppi;

/**
 *
 * @author Kulmala
 */
public class Pelaaja extends Hahmo{
	private int ruoka;
	private Reppu reppu;
        private boolean testaustila = false;
        //private Taso tasoNyt; lisäsin yläluokkaan
        //private MetroHack peli;
	
	public Pelaaja(int vitutusMax, String nimi, int x, int y){
		super(vitutusMax, nimi, x, y);
		this.ruoka = 10;
		this.reppu = new Reppu(10, 10);
                this.merkki = '@';    
	}
        
        
        
//        public void vaihdaTasoa(Taso t){
//            this.tasoNyt = t;
//        }
        
        public void setTestMode(boolean b){
            this.testaustila = b;
        }
        
        public Reppu getReppu(){
            return this.reppu;
        }
        
        public boolean liiku(int dx, int dy){
            Tiilityyppi t = this.tasoNyt.getTiili(x+dx, y+dy);
            if (t == Tiilityyppi.SEINA && !testaustila){
                //System.out.println("Wall!");
                return false; 
            }
            //System.out.println(t);
            this.x += dx;
            this.y += dy;
            //this.peli.tulosta(this.x+","+this.y);
            return true;
        }

    public void paivita(int moneskoVuoro) {
        if (vitutus > 0 && moneskoVuoro%7 == 0) { //hiilaaminen ajan mittaan
            vitutus--;
        } else if (moneskoVuoro%13 == 0) { //kovakoodattuja taikalukuja <3
            ruoka--;
        }
        
        if (vitutus >= vitutusMax || ruoka <= 0) {
            //game over!
        }
    }
    
    public int getRuoka(){
        return this.ruoka;
    }
    
    public ArrayList<String> getBuffNames(){
        ArrayList<String> buffs = new ArrayList<>();
        if (ruoka > 5) {
            buffs.add("kylläinen");
        } else {
            buffs.add("nälkäinen");
        }
        buffs.add("ducks");
        return buffs;
    }

    public String getNimi() {
        return this.nimi;
    }



}