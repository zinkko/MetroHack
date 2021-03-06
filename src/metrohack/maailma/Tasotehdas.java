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
import metrohack.maailma.entities.Monsteri;

/**
 *
 * @author ilari
 */
public class Tasotehdas {

    private Random random;
    private int kokoX, kokoY;
    private ArrayList<String> tavarat;

    public Tasotehdas() {
        kokoX = 100;
        kokoY = 30;
        this.random = new Random();
        this.tavarat = new ArrayList<>();
        luoTavaralista();
    }

    public Taso luoTaso() {
        Taso taso = new Taso(new ArrayList<Linja>());
        //Huone siemen = new Huone(taso.getTiilet(),10,10,kokoX/2-5,kokoY/3-5);
        //taso.lisaaHuone(siemen);
        //tiputaReunoilta(taso);
        blockPlacement(taso);
        
        asetaHahmot(taso);
        //taso.lisaaHahmo(new Monsteri(5, "viemärirotta",60,25,1));

        return taso;
    }
    
    private void asetaHahmot(Taso t){;
        for (Huone h: t.huoneet()){
            int x = h.getX() + h.getLeveys()/2;
            int y = h.getY() + h.getPituus()/2;
            Monsteri m = new Monsteri(10,"höyrykaivuri",x,y,1);
            t.lisaaHahmo(m);
            m.asetaTaso(t);
            
            
        }
    }


    private void blockPlacement(Taso taso) {

        int pituus = 30;
        int leveys = 10;
        
        int n = 1;
        
        for (int x = 0; x <= kokoX - pituus; x += pituus) {
            for (int y = 0; y <= kokoY - leveys; y += leveys) {
                int a = random.nextInt(20);
                int b = random.nextInt(4) + random.nextInt(4); // painota keskelle
                if (n==9){
                    taso.lisaaHuone(new Metrolaituri(taso.getTiilet(),
                            new Linja(),x + a/2, y + b/2, pituus - a, leveys - b));
                    //taso.lisaaTavara(tavarat.get(random.nextInt(9))); //joskus tästä tulee fiksu testaus...
                }else{
                    taso.lisaaHuone(new Huone(taso.getTiilet(), x + a/2, y + b/2, pituus - a, leveys - b));
                    //System.out.println(x+", "+y);
                    //taso.lisaaHahmo(new Monsteri(10,"höyrykaivuri", x+ a/2+2 ,y+ b/2+2 ,1)); //koevaiheessa joka huoneeseen monsu
                }
                n++;
            }
        }
        blockPlacementCorridors(taso);
    }

    private void blockPlacementCorridors(Taso taso) {
        List<Huone> huoneet = taso.huoneet();
        List<Huone> kaytavat = new LinkedList<>();
        Huone eka = huoneet.get(0);
        Huone toka;
        for (int i = 1; i < huoneet.size(); i++) {
            toka = huoneet.get(i);
            int[] xypl = yhdyskaytava(eka, toka); // [x,y,pituus,leveys]
            if (xypl != null) {
                kaytavat.add(new Huone(taso.getTiilet(), xypl));
            } else {
  //              System.out.println("was null");
  //              System.out.println(eka + " <> " + toka);
            }

            eka = toka;
        }
        int[] xypl1 = yhdyskaytava(huoneet.get(0), huoneet.get(3));
        int[] xypl2 = yhdyskaytava(huoneet.get(2), huoneet.get(5));
        int[] xypl3 = yhdyskaytava(huoneet.get(4), huoneet.get(7));
        kaytavat.add(new Huone(taso.getTiilet(), xypl1));
        kaytavat.add(new Huone(taso.getTiilet(), xypl2));
        kaytavat.add(new Huone(taso.getTiilet(), xypl3));

        huoneet.addAll(kaytavat);
    }

    private int[] yhdyskaytava(Huone eka, Huone toka) {
        int[] palauta = new int[4];

        boolean vertical = false;

        if (eka.oikeaReuna() > toka.getX() && eka.getX() < toka.oikeaReuna()) { // x
            palauta[0] = Math.max(eka.getX(), toka.getX()) + 2; // x
            palauta[1] = Math.min(eka.ylaReuna(), toka.ylaReuna()); //y
            palauta[2] = 3; // koko-x
            palauta[3] = Math.max(eka.getY(), toka.getY()) - Math.min(eka.ylaReuna(), toka.ylaReuna()) + 1;
        } else if (eka.ylaReuna() > toka.getY() && eka.getY() < toka.ylaReuna()) {
            palauta[0] = Math.min(eka.oikeaReuna(), toka.oikeaReuna()); // x
            palauta[1] = Math.max(eka.getY(), toka.getY()) + 2; //y
            palauta[2] = Math.max(eka.getX(), toka.getX()) - Math.min(eka.oikeaReuna(), toka.oikeaReuna()) + 1;
            palauta[3] = 3;
            vertical = true;
        } else {
            return null;
        }

        Huone huone1, huone2;
        int x1, y1, x2, y2;

        if (vertical) {
            if (eka.getX() < toka.getX()) {
                huone1 = eka;
                huone2 = toka;
            } else {
                huone2 = eka;
                huone1 = toka;
            }
            y1 = palauta[1] +1; y2 = y1;
            x1 = huone1.oikeaReuna();
            x2 = huone2.getX();
        } else {
            if (eka.getY() < toka.getY()) {
                huone1 = eka;
                huone2 = toka;
            } else {
                huone2 = eka;
                huone1 = toka;
            }
            x1 = palauta[0] +1; x2 = x1;
            y1 = huone1.ylaReuna();
            y2 = huone2.getY();
        }

        boolean b1 = huone1.asetaOvi(x1, y1);
        boolean b2 = huone2.asetaOvi(x2, y2);
        
        if (!(b1 && b2)){
    //        System.out.println(x1+","+y1+" "+x2+","+y2);
        }

        return palauta;
    }

    private void luoTavaralista() {
        tavarat.add("kertalippu");
        tavarat.add("kausilippu");
        tavarat.add("patonki");
        tavarat.add("kebab-ateria");
        tavarat.add("muffini");
        tavarat.add("omena");
        tavarat.add("korvatulpat");
        tavarat.add("rahakasa");
    }
}
