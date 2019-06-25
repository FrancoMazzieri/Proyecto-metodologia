
package GameObjects;

import Graphics.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author emalu
 */
public enum Size {
    BIG(2, Assets.bigs), MED(2, Assets.smalls), SMALL(2, Assets.tinies), TINY(0, null);
    public int quantity;
    public BufferedImage[] textures;
    
    private Size(int quantity,BufferedImage[] textures){
        this.quantity = quantity;
        this.textures = textures;
        
    }
    
}
