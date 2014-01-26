/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        
        /* 
           | 3 | 6 | 9 | n+2 | y
           | 2 | 5 | 8 | n+1 | ^
           | 1 | 4 | 7 | ... | |__ x 
        */
        
        for (int x=0;x<=kokoX-pituus;x+=pituus){
            for (int y=0;y<=kokoY-leveys;y+=leveys){
                int a = random.nextInt(20);
                int b = random.nextInt(4) + random.nextInt(4); // painota keskelle
                taso.lisaaHuone(new Huone(taso.getTiilet(),x+a/2,y+b/2,pituus-a,leveys-b));
            }
        }
        blockPlacementCorridors(taso);
    }
    
    private void blockPlacementCorridors(Taso taso){
        List<Huone> huoneet = taso.huoneet();
        List<Huone> kaytavat = new LinkedList<>();
        Huone eka = huoneet.get(0);
        Huone toka;
        for (int i=1;i<huoneet.size();i++){
            toka= huoneet.get(i);
            int[] xypl = yhdysKaytava(eka, toka); // [x,y,pituus,leveys]
            if (xypl != null){
                kaytavat.add(new Huone(taso.getTiilet(),xypl));
            }else{
                System.out.println("was null");
            }
            
            eka = toka;
        }
        System.out.println(kaytavat.size());
        for (Huone h : kaytavat){
            System.out.println(h.getX()+","+h.getY());
            System.out.println(h.getPituus()+" "+h.getLeveys());
            System.out.println("");
        }
        huoneet.addAll(kaytavat);
    }
    
    private int[] yhdysKaytava(Huone eka, Huone toka){
        int [] palauta = new int[4];
        
        System.out.println(eka.getX() +","+ eka.getY() +" "+ eka.getPituus() +":"+ eka.getLeveys());
        System.out.println(toka.getX() +","+ toka.getY() +" "+ toka.getPituus() +":"+ toka.getLeveys());
        
        if (eka.oikeaReuna() > toka.getX() && eka.getX() < toka.oikeaReuna()){ // x
            palauta[0] = Math.max(eka.getX(),toka.getX()) + 2; // x
            palauta[1] = Math.max(eka.ylaReuna(), toka.ylaReuna()); //y
            palauta[2] = 3; // koko-x
            palauta[3] = Math.max(eka.getY(), toka.getY())-Math.min(eka.ylaReuna(), toka.ylaReuna());
        }else if (eka.oikeaReuna() < toka.getX() && toka.oikeaReuna() < eka.getX()){
            palauta[0] = Math.min(eka.oikeaReuna(),toka.oikeaReuna()); // x
            palauta[1] = Math.max(eka.getY(), toka.getY()) +2; //y
            palauta[2] = Math.max(eka.getX(), toka.getX())-Math.min(eka.oikeaReuna(),toka.oikeaReuna());
            palauta[3] = 3;
        }else{
            return null;
        }
        
        return palauta;
    }
}