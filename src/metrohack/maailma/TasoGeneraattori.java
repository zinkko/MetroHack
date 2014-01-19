/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ilari
 */
public class TasoGeneraattori {
    
    private Random random;
    private int kokoX, kokoY;
    
    public TasoGeneraattori(){
        kokoX = 100;
        kokoY = 30;
        this.random = new Random();
    }
    
    public Taso luoTaso(){
        Taso taso = new Taso(new ArrayList<Linja>());
        
        //Huone siemen = new Huone(taso.getTiilet(),10,10,kokoX/2-5,kokoY/3-5);
        //taso.lisaaHuone(siemen);
        
        //tiputaReunoilta(taso);
        blockPlacement(taso);
        
        return taso;
    }
    
    private void tiputaReunoilta(Taso taso){
        while (true){
            int[] xypl = new int[4];// x, y, pituus, leveys
            break;
        }
    }
    
    private void blockPlacement(Taso taso){
        
        int pituus = 30;
        int leveys = 10;
        
        for (int x=0;x<=kokoX-pituus;x+=pituus){
            for (int y=0;y<=kokoY-leveys;y+=leveys){
                int a = random.nextInt(20);
                int b = random.nextInt(4) + random.nextInt(4); // painota keskelle
                taso.lisaaHuone(new Huone(taso.getTiilet(),pituus-a,leveys-b,x+a/2,y+b/2));
            }
        }
        
        
    }
}
