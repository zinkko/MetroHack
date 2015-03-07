/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma.entities;

import java.util.Random;
import metrohack.maailma.Taso;
import metrohack.maailma.Tiilityyppi;

/**
 *
 * @author ilari
 */
public class Monsteri extends Hahmo {
    private Pelaaja pelaaja;
    private final int nopeus; //nopeus per yksi vuoro
    private int tavoiteX;
    private int tavoiteY;
    private int vanhaX;
    private int vanhaY;
    private int suunta; //kuin numeronäppäimistö, tästä joskus enum?
    //private Taso tasoNyt; yläluokassa
    
    public Monsteri(int vitutus, String nimi, int x, int y, int nopeus){
        super(vitutus,nimi,x,y);
        //this.pelaaja = pelaaja;
        this.nopeus = nopeus;
        super.merkki = 'm';
        this.suunta = new Random().nextInt(10)+1; //arvotaan default-suunta  
        this.vanhaX = x;
        this.vanhaY = y;
    }
    
    private boolean nakeekoPelaajan(){
        return false;
    }
    
//    public void vaihdaTasoa(Taso t){ //Tätä pitää kutsua jossakin!
//        this.tasoNyt = t;
//    }
    
    //tän kun toteuttaa, niin homma ehkä toimii. tarvii vaan tiedon siitä, törmääkö liikkuessaan
    private boolean liikuYksi(int dx, int dy){ 
        //if (tasoNyt==null) System.out.println("taso null");
            Tiilityyppi t = super.tasoNyt.getTiili(x+dx, y+dy);
            if (t == Tiilityyppi.SEINA){
                return false; 
            }
            //System.out.println(t);
            
            vanhaX = x;
            vanhaY = y;
            
            this.x += dx;
            this.y += dy;
            //this.peli.tulosta(this.x+","+this.y);
            return true;
    }
    
    private void valitseSuunta(){
        int dy = 0;
        int dx = 0;
        if (suunta < 4) {
            dy++;
        } else if (suunta > 6) {
            dy--;
        }
        
        if (suunta%3 == 0) {
            dx++;
        } else if (suunta%3 == 1) {
            dx--;
        }
        if (!liikuYksi(dx, dy)) {
            liikuYksi(-1 * dx, -1 * dy);
        }
    }
    
    public void liiku(){
        if (nakeekoPelaajan()){
            liikuKohtiPelaajaa();
        } else {
            liikuSatunnaisesti();
        }
    }
    
    public void peruLiike() {
        x = vanhaX;
        y = vanhaY;
    }
    
    public void liikuKohtiPelaajaa(){
        
    }
    
    public void liikuSatunnaisesti(){
//        if (Math.abs(tavoiteX-x) + Math.abs(tavoiteY-y)<10){
//            arvoUusiSatunnainenPaikka();
//        }
//        int muutosY = 0;
//        int muutosX = 0;
//        if (tavoiteX<x){
//            muutosX--;
//        } else if (tavoiteY<y){
//            muutosY--;
//        } else if (tavoiteX>x){
//            muutosX++;
//        } else if (tavoiteY>y){
//            muutosY++;
//        }
        Random random = new Random();
        if (random.nextDouble() < 0.5) { //puolet kerroista jatkaa samaan suuntaan kuin äsken
            suunta = random.nextInt(10) + 1;
        }
        
        valitseSuunta();
//        
//        if (!liikuYksi(muutosX, muutosY)){ //jos liikkuminen ei onnistu, kokeillaan eri suuntaan, jotta monsu ei ehkä jää jumiin
//            liikuYksi(-1*muutosX, -1*muutosY);
//        }
    }
    
    private void arvoUusiSatunnainenPaikka(){
        Random random = new Random();
        this.tavoiteX = random.nextInt(90) + 5; //jee kovakoodi
        this.tavoiteY = random.nextInt(25) + 5;
    }
}
