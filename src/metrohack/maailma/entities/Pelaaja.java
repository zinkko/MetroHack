/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import metrohack.MetroHack;
import metrohack.maailma.Taso;
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
	
	public Pelaaja(int vitutus, String nimi, int x, int y){
		super(vitutus, nimi, x, y);
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

}