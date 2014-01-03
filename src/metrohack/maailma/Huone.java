/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrohack.maailma;

import java.util.List;

/**
 *
 * @author ilari
 */
public class Huone {
    
    private List<Huone> viereiset;
    private List<Tiili> osat;
    
    public void piirra(char[][] map){
        for (Tiili t : osat){
            t.piirra(map);
        }
    }
}
