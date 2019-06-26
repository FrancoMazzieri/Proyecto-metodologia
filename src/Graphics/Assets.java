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
    
    //Explociones
    	public static BufferedImage[] exp = new BufferedImage[9];
        
    //Ufo
        public static BufferedImage ufo;
            
    public static void init() {
    
		player = Loader.ImageLoader("/ships/player.png");
		
		speed = Loader.ImageLoader("/effects/fire08.png");
		
		blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");
		
		greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
		
		redLaser = Loader.ImageLoader("/lasers/laserRed01.png");
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = Loader.ImageLoader("/Meteors/meteorGrey_big"+(i+1)+".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = Loader.ImageLoader("/Meteors/meteorBrown_med"+(i+1)+".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = Loader.ImageLoader("/Meteors/meteorBrown_small"+(i+1)+".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = Loader.ImageLoader("/Meteors/meteorBrown_tiny"+(i+1)+".png");
                for (int i = 0; i < exp.length; i++) {
                    exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
                    
                    ufo = Loader.ImageLoader("/ships/ufo.png");
        }

    }
}
