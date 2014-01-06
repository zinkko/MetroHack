/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrohack.maailma;

/**
 *
 * @author Kulmala
 */
public class Metrolaituri extends Huone {
    
	private Linja linja;
	private String kuvaus;
	
	public Metrolaituri(Linja linja, int pituus, int leveys){
		super(pituus, leveys);
		this.linja = linja;
		this.kuvaus = "TÃ¤ltÃ¤ laiturilta kulkee linja " + linja + ". Otattehan metrolla matkustaessanne huomioon, ettÃ¤ ilman "
		+ "asianomaista lippua matkustavilta voidaan periÃ¤ tarkastusmaksu 20 euroa.";
	}
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
