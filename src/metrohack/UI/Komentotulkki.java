/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.UI;

import metrohack.MetroHack;
import metrohack.maailma.entities.Pelaaja;

/**
 *
 * @author ilari
 */
public class Komentotulkki {
    
    private static final String MOVE_CHARS = "wasd";
    private static final char CMD_CHAR = '$';
    private boolean cmdMode = false;
    private String komento = "";
    private final MetroHack peli;
    private Piirtaja piirtaja;
    
    public Komentotulkki(MetroHack peli){
        this.peli = peli;
    }
    
    public void give(char cmd){
        if (this.piirtaja==null){
            this.piirtaja=peli.getPiirtaja();
        }
        if (this.cmdMode){
            this.komento(cmd);
            return;
        }
        String command = new String(new char[]{cmd});
        
        if(Komentotulkki.MOVE_CHARS.contains(command)){
            moveCommand(command);
        }else if (cmd==CMD_CHAR){
            this.cmdMode = true;
            this.komento(CMD_CHAR);
        }
        // lisää vaihtoehtoja
        
        this.peli.piirra();
    }
    
    private void komento(char cmd){
        if (cmd == '\n'){
            this.applyCommand(piirtaja.getKomento());
            this.piirtaja.setPitkaKomento("");
            this.cmdMode = false;
        }else if (cmd == '\b'){ // del char
            this.piirtaja.backspace();
        }else if ((int)cmd == 65535){ // this will come up with SHIFT,CTRL, etc
            //skip
        }else{
            this.piirtaja.addCharToCmd(cmd);
        }
        System.out.print(cmd);
        System.out.println(" "+(int) cmd);
    }
    
    private void applyCommand(String cmd){
        System.out.println("command!\nto be implemented...");
        switch (cmd){
            case "ankka":
                this.piirtaja.piirraYstavaAnkka();
                break;
            case "test":
                this.peli.setWalkThruWalls(true);
                break;
            case "test off":
                this.peli.setWalkThruWalls(false);
                break;
            case "reppu":
                this.piirtaja.tulosta(peli.getPelaaja().getReppu().repunSisalto());
                break;
            default:
                this.piirtaja.tulosta("outo komento :O");
        }
    }
            
    
    private void moveCommand(String komento){
        Pelaaja pelaaja = this.peli.getPelaaja();
        int dx,dy;
        switch(komento){
            case "w":
                dx=0;
                dy=1;
                break;
            case "s":
                dx=0;
                dy=-1;
                break;
            case "a":
                dx=-1;
                dy=0;
                break;
            case "d":
                dx=1;
                dy=0;
                break;
            default:
                System.out.println("err");
                dx=0;
                dy=0;
        }
        //this.peli.tulosta("liikuit suuntaan "+ dx + "," + dy);
        pelaaja.liiku(dx, dy);
    }
}
