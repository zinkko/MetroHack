/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

import metrohack.maailma.Taso;
import metrohack.maailma.Tiilityyppi;

/**
 *
 * @author Kulmala
 */
public class Pelaaja extends Hahmo{
	private int ruoka;
	private Reppu reppu;
        private boolean testing = false;
        private Taso tasoNyt;
	
	public Pelaaja(int vitutus, String nimi, int x, int y){
		super(vitutus, nimi, x, y);
		this.ruoka = 10;
		this.reppu = new Reppu(10, 10);
                this.merkki = '@';
	}
        
        public void vaihdaTasoa(Taso t){
            this.tasoNyt = t;
        }
        
        public void setTestMode(boolean b){
            this.testing = b;
        }
        
        public void liiku(int dx, int dy){
            Tiilityyppi t = this.tasoNyt.getTiili(x+dx, y+dy);
            if (t== Tiilityyppi.SEINA && !testing){
                //System.out.println("Wall!");
                return; // don't walk trhu walls
            }
            //System.out.println(t);
            this.x += dx;
            this.y += dy;
        }

}