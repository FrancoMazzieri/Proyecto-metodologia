/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Chory77
 */
public class Assets {
    
    public static BufferedImage player;
    
    public static BufferedImage speed;
    
    public static void init(){
        player = Loader.ImageLoader("/Naves/player.png");
        speed = Loader.ImageLoader("/spaceshooter.PNG.Effects/fire08.png");

}
}
