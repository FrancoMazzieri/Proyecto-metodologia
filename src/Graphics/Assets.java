/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;


/**
 *
 * @author Chory77
 */
public class Assets {

    public static boolean loaded = false;
    public static float count = 0;
    public static float MAX_COUNT = 46;
    public static BufferedImage player;
    
    //Efectos
    public static BufferedImage speed;
    
    //Explosiones
    public static BufferedImage[] exp = new BufferedImage[9];

    //Lasers
    public static BufferedImage blueLaser, greenLaser, redLaser;
    
    //Meteoros
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];

    //Ufo
    public static BufferedImage ufo;

    // numbers
    public static BufferedImage[] numbers = new BufferedImage[11];
    public static BufferedImage life;

    //Fuentes
    public static Font fontBig;
    public static Font fontMed;
    
    public static Clip backGroundMusic, explosion, playerLoose, playerShoot, ufoShoot;
    
    // ui
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
	

    public static void init() {

        player = loadImage("/ships/player.png");

        speed = loadImage("/effects/fire08.png");

        blueLaser = loadImage("/lasers/laserBlue01.png");

        greenLaser = loadImage("/lasers/laserGreen11.png");

        redLaser = loadImage("/lasers/laserRed01.png");

        life = loadImage("/others/life.png");
        
        ufo = loadImage("/ships/ufo.png");

        fontBig = loadFont("/fonts/futureFont.ttf", 42); //mensajes en tamaño grandes
        
        fontMed = loadFont("/fonts/futureFont.ttf", 20); //mensajes en tamaño mediano
        
        for (int i = 0; i < bigs.length; i++) {
            bigs[i] = loadImage("/Meteors/meteorGrey_big" + (i + 1) + ".png");
        }

        for (int i = 0; i < meds.length; i++) {
            meds[i] = loadImage("/Meteors/meteorBrown_med" + (i + 1) + ".png");
        }

        for (int i = 0; i < smalls.length; i++) {
            smalls[i] = loadImage("/Meteors/meteorBrown_small" + (i + 1) + ".png");
        }

        for (int i = 0; i < tinies.length; i++) {
            tinies[i] = loadImage("/Meteors/meteorBrown_tiny" + (i + 1) + ".png");
        }
        for (int i = 0; i < exp.length; i++) {
            exp[i] = loadImage("/explosion/" + i + ".png");

        }
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = loadImage("/numbers/" +( i )+ ".png");
        }
        
        //cargamos los sonidos del juego
        backGroundMusic = loadSound("/sounds/backGroundMusic.wav");
        explosion = loadSound("/sounds/explosion.wav");
        playerLoose = loadSound("/sounds/playerLoose.wav");
        playerShoot = loadSound("/sounds/playerShoot.wav");
        ufoShoot = loadSound("/sounds/ufoShoot.wav");
        
        greyBtn = loadImage("/ui/grey_button.png");
	blueBtn = loadImage("/ui/blue_button.png");

        //*********************************************************************
        loaded = true;
    }
    
    public static BufferedImage loadImage(String path){
        count ++;
        return Loader.ImageLoader(path);
    }
    
    public static Font loadFont(String path, int size){
        count ++;
        return Loader.loadFont(path, size);
    }
    
    public static Clip loadSound(String path){
        count ++;
        return Loader.loadSound(path);
    }
}
