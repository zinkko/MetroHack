/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

/**
 *
 * @author Kulmala
 */
public class Pelaaja extends Hahmo{
	private int ruoka;
	private Reppu reppu;
	
	public Pelaaja(int vitutus, String nimi, int x, int y){
		super(vitutus, nimi, x, y);
		this.ruoka = 10;
		this.reppu = new Reppu(10, 10);
	}
	
	public void metodi(){
	}

}