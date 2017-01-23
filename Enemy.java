import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public abstract class Enemy extends animate{
  public Direction currentDirection = Direction.Right;
  public int intHealth;
  
  /**Implemented by subclasses to make the subclasses of Enemy behave differently. This method should be called at every frame.*/
 public abstract void updateBehaviour();
  
  /**Constructs a new Enemy object with a specific width amd height. Each BufferedImage object represents
    * a specific state (ie active, inactive, jumping, etc). There can be many states, or there 
    * can be only one state, depending on the object. The individual frames of the sprite
    * will be saved in the sprites[][] array.
    * @param intWidth the width of the enemy
    * @param intHeight the height of the enemy
    * @param intHealth the amount of health that the enemy will have
    * @param SpriteSheet Array of BufferedImages for the animated sprite. Each BufferedImage will contain different frames
    these frames must be the specified width and height) and will correspond to different states of the object.
    The first BufferedImage in the array will be the default state of the object.
    */
  public Enemy(int intWidth, int intHeight, int intHealth, BufferedImage SpriteSheet[]){
    super(intWidth, intHeight, SpriteSheet);
  }
  
  /**Constructs a new Enemy object with a specific width and height at a specific location
    *@param intX initial X coordinate of the Enemy
    * @param intY initial Y coordinate of the Enemy
    * @see #Enemy(int, int, BufferedImage[])
    */
  public Enemy(int intX, int intY, int intWidth, int intHeight, int intHealth, BufferedImage SpriteSheet[]){
    super(intX, intY, intWidth, intHeight, SpriteSheet);
  }
  
  
}