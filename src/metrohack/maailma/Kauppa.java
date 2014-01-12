/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma;

import java.util.List;

/**
 *
 * @author Kulmala
 */
public class Kauppa extends Huone {
    

	private String kuvaus;
	
	public Kauppa(List<Tiili> ankka,int pituus, int leveys,int x,int y){
		super(ankka,pituus, leveys,x,y);
		this.kuvaus = "Le Kauppa"; //insert järkevää here
	}
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
