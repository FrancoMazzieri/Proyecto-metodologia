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
    //Efectos
    public static BufferedImage speed;
    //Lasers
    public static BufferedImage blueLaser, greenLaser, redLaser;
    //Meteoros
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
            
            
    public static void init() {
        player = Loader.ImageLoader("/Naves/player.png");
        speed = Loader.ImageLoader("/effects/fire08.png");
        blueLaser = Loader.ImageLoader("/Lasers/laserBlue01.png");
        greenLaser = Loader.ImageLoader("/Lasers/laserGreen11.png");
        redLaser = Loader.ImageLoader("/Lasers/laserRed01.png");
        for(int i=0; i < bigs.length; i++)
             bigs[i] = Loader.ImageLoader("/Meteors/meteorBrown_big"+(i+1)+".png");
        for(int i=0; i < meds.length; i++)
             bigs[i] = Loader.ImageLoader("/Meteors/meteorBrown_med"+(i+1)+".png");
        for(int i=0; i < smalls.length; i++)
             bigs[i] = Loader.ImageLoader("/Meteors/meteorBrown_small"+(i+1)+".png");
        for(int i=0; i < tinies.length; i++)
             bigs[i] = Loader.ImageLoader("/Meteors/meteorBrown_tiny"+(i+1)+".png");
        

    }
}
