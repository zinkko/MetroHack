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
public class KomentoTulkki {
    
    private static final String MOVE_CHARS = "ikjluom.";
    private static final char CMD_CHAR = '$';
    private boolean cmdMode = false;
    private String komento = "";
    private final MetroHack peli;
    private Piirtaja piirtaja;
    
    public KomentoTulkki(MetroHack peli){
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
        
        if(KomentoTulkki.MOVE_CHARS.contains(command)){
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
            default:
                this.piirtaja.tulosta("outo komento :O");
        }
    }
            
    
    private void moveCommand(String komento){
        Pelaaja pelaaja = this.peli.getPelaaja();
        int dx,dy;
        switch(komento){
            case "j":
                dx=0;
                dy=-1;
                break;
            case "l":
                dx=0;
                dy=1;
                break;
            case "k":
                dx=1;
                dy=0;
                break;
            case "i":
                dx=-1;
                dy=0;
                break;
            case "u":
                dx=-1;
                dy=-1;
                break;
            case ".":
                dx=1;
                dy=1;
                break;
            case "o":
                dx=-1;
                dy=1;
                break;
            case "m":
                dx=1;
                dy=-1;
                break;
                
            default:
                dx=0;
                dy=0;
        }
        //this.peli.tulosta("liikuit suuntaan "+ dx + "," + dy);
        pelaaja.liiku(dx, dy);
    }
}
