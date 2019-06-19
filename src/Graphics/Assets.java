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

    public static void init() {
        player = Loader.ImageLoader("/Naves/player.png");
        speed = Loader.ImageLoader("/effects/fire08.png");
        blueLaser = Loader.ImageLoader("/Lasers/laserBlue01.png");
        greenLaser = Loader.ImageLoader("/Lasers/laserGreen11.png");
        redLaser = Loader.ImageLoader("/Lasers/laserRed01.png");

    }
}
