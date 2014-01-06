/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma.entities;

/**
 *
 * @author Kulmala
 */
public class Reppu{
	private int rahat;
	private Tavara[] tavarat;

	public Reppu(int rahat, int max){
		this.rahat = rahat;
		this.tavarat = new Tavara[max];
	}
	
	public boolean onkoTavaraa(String tavaranNimi){
		for (Tavara t: tavarat){
			if (t.getNimi().equals(tavaranNimi)){
				return true;
			}
		}
		return false;
	}
}
