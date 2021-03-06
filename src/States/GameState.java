/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import GameObjects.Meteor;
import GameObjects.MovingObject;
import GameObjects.Player;
import GameObjects.Size;
import GameObjects.Ufo;
import GameObjects.constans;
import Graphics.Animation;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Chory77
 */
public class GameState {

    private Player player;

    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();

    private ArrayList<Animation> explosions = new ArrayList<Animation>();

    private int score = 0;

    private int lives = 3;

    private int meteors;

    public GameState() {
        player = new Player(new Vector2D(constans.WIDTH / 2 - Assets.player.getWidth() / 2, constans.HEIGHT / 2
                - Assets.player.getHeight() / 2), new Vector2D(), constans.PLAYER_MAX_VEL, Assets.player, this);

        movingObjects.add(player);

        meteors = 1;

        startWave();

    }

    public void addScore(int value) {
        score += value;

    }

    public void divideMeteor(Meteor meteor) {
        Size size = meteor.getSize();
        BufferedImage[] textures = size.textures;
        Size newSize = null;
        switch (size) {
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }
        for (int i = 0; i < size.quantity; i++) {
            movingObjects.add(new Meteor(
                    meteor.getPosicion(),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),
                    constans.METEOR_VEL * Math.random() + 1,
                    textures[(int) (Math.random() * textures.length)],
                    this,
                    newSize
            ));
        }
    }

    private void startWave() {

        double x, y;
    
        for (int i = 0; i < meteors; i++) {

            x = i % 2 == 0 ? Math.random() * constans.WIDTH : 0;
            y = i % 2 == 0 ? 0 : Math.random() * constans.HEIGHT;

            BufferedImage texture = Assets.bigs[(int) (Math.random() * Assets.bigs.length)];

            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),
                    constans.METEOR_VEL * Math.random() + 1,
                    texture,
                    this,
                    Size.BIG
            ));
        }

        meteors++;
        spawnUfo();
    }

    public void playExplosion(Vector2D position) {
        explosions.add(new Animation(Assets.exp,
                50,
                position.subtract(new Vector2D(Assets.exp[0].getWidth() / 2, Assets.exp[0].getHeight() / 2))));
    }

    private void spawnUfo() {

        int rand = (int) (Math.random() * 2);

        double x = rand == 0 ? (Math.random() * constans.WIDTH) : constans.WIDTH;
        double y = rand == 0 ? constans.HEIGHT : (Math.random() * constans.HEIGHT);

        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        double posX, posY;

        posX = Math.random() * constans.WIDTH / 2;
        posY = Math.random() * constans.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (constans.WIDTH / 2) + constans.WIDTH / 2;
        posY = Math.random() * constans.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * constans.WIDTH / 2;
        posY = Math.random() * (constans.HEIGHT / 2) + constans.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (constans.WIDTH / 2) + constans.WIDTH / 2;
        posY = Math.random() * (constans.HEIGHT / 2) + constans.HEIGHT / 2;
        path.add(new Vector2D(posX, posY));

        movingObjects.add(new Ufo(
                new Vector2D(x, y),
                new Vector2D(),
                constans.UFO_MAX_VEL,
                Assets.ufo,
                path,
                this
        ));

    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }

        for (int i = 0; i < explosions.size(); i++) {
            Animation anim = explosions.get(i);
            anim.update();
            if (!anim.isRunning()) {
                explosions.remove(i);

            }
        }

        for (int i = 0; i < movingObjects.size(); i++) {
            if (movingObjects.get(i) instanceof Meteor) {
                return;
            }
        }

        startWave();
        
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        

        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }

        for (int i = 0; i < explosions.size(); i++) {
            Animation anim = explosions.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int) anim.getPosition().getX(), (int) anim.getPosition().getY(), null);
        }

        drawScore(g);
        drawLives(g);
    }

    private void drawScore(Graphics g) {
        Vector2D pos = new Vector2D(850, 25);

        String scoreToString = Integer.toString(score);

        for (int i = 0; i < scoreToString.length(); i++) {
            
            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))], (int) pos.getX(), (int) pos.getY(),
                    null);
            
            pos.setX(pos.getY() + 700);
        }

    }

    private void drawLives(Graphics g) {

        Vector2D livePosition = new Vector2D(25, 25);

        g.drawImage(Assets.life, (int) livePosition.getX(), (int) livePosition.getY(), null);

        g.drawImage(Assets.numbers[10], (int) livePosition.getX() + 40,
                (int) livePosition.getY() + 5, null);

        String livesToString = Integer.toString(lives);

        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());

        for (int i = 0; i < livesToString.length(); i++) {
            int number = Integer.parseInt(livesToString.substring(i, i + 1));

            if (number <= 0) {
                break;
            }
            g.drawImage(Assets.numbers[number],
                    (int) pos.getX() + 60, (int) pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }

    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    public Player getPlayer() {
        return player;
    }

    public void subtractLife() {
        lives--;
    }
}
