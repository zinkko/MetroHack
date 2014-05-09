/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma.entities;

import java.util.Random;

/**
 *
 * @author ilari
 */
public class Monsteri extends Hahmo {
    private Pelaaja pelaaja;
    private final int nopeus; //nopeus per yksi vuoro
    private int tavoiteX;
    private int tavoiteY;
    
    public Monsteri(int vitutus, String nimi, int x, int y, int nopeus){
        super(vitutus,nimi,x,y);
        //this.pelaaja = pelaaja;
        this.nopeus = nopeus;
        super.merkki = 'm';
    }
    
    private boolean nakeekoPelaajan(){
        return false;
    }
    
    //tän kun toteuttaa, niin homma ehkä toimii. tarvii vaan tiedon siitä, törmääkö liikkuessaan
    private boolean liikuYksi(int muutosX, int muutosY){ 
        return false;
    }
    
    public void liiku(){
        if (nakeekoPelaajan()){
            liikuKohtiPelaajaa();
        } else {
            liikuSatunnaisesti();
        }
    }
    
    public void liikuKohtiPelaajaa(){
        
    }
    
    public void liikuSatunnaisesti(){
        if (Math.abs(tavoiteX-x) + Math.abs(tavoiteY-y)<10){
            arvoUusiSatunnainenPaikka();
        }
        int muutosY = 0;
        int muutosX = 0;
        if (tavoiteX<x){
            muutosX--;
        } else if (tavoiteY<y){
            muutosY--;
        } else if (tavoiteX>x){
            muutosX++;
        } else if (tavoiteY>y){
            muutosY++;
        }
        if (!liikuYksi(muutosX, muutosY)){ //jos liikkuminen ei onnistu, kokeillaan eri suuntaan, jotta monsu ei ehkä jää jumiin
            liikuYksi(-1*muutosX, -1*muutosY);
        }
    }
    
    private void arvoUusiSatunnainenPaikka(){
        Random random = new Random();
        this.tavoiteX = random.nextInt(90) + 5; //jee kovakoodi
        this.tavoiteY = random.nextInt(25) + 5;
    }
}
